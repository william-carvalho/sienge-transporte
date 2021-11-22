package com.sienge.transporte.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sienge.transporte.domain.Transporte;
import com.sienge.transporte.domain.TransporteRodovia;
import com.sienge.transporte.dto.TransporteRodoviaDto;
import com.sienge.transporte.exception.BusinessException;
import com.sienge.transporte.repository.TransporteRepository;
import com.sienge.transporte.repository.TransporteRodoviaRepository;



@Service
public class TransporteRodoviaService {
	
	@Autowired
	TransporteRepository transporteRepository;
	
	@Autowired
	TransporteRodoviaRepository transporteRodoviaRepository;
	
	@Transactional
	public List<TransporteRodovia> create(TransporteRodoviaDto trasnporteRodoviaDto)
	{
		
		if(trasnporteRodoviaDto == null){
			throw new BusinessException("Erro ao cadastrar informações.");
		}
		
		if(trasnporteRodoviaDto.getTransporte() == null){
			throw new BusinessException("Transporte não encotrado.");
		}
		
		if(trasnporteRodoviaDto.getTransporte().getDescricao() == null || trasnporteRodoviaDto.getTransporte().getDescricao().isEmpty()){
			throw new BusinessException("Descrição inválida.");
		}
		
		if(trasnporteRodoviaDto.getTransporte().getCarga() <= 0){
			throw new BusinessException("Carga inválida.");
		}
		
		if(trasnporteRodoviaDto.getTransporte().getVeiculo() == null){
			throw new BusinessException("Veículo inválido.");
		}	
		
		if(trasnporteRodoviaDto.getRodovia() == null || trasnporteRodoviaDto.getRodovia().isEmpty()){
			throw new BusinessException("Rodovia(s) não encontradas.");
		}
		
		
		trasnporteRodoviaDto.getTransporte().setDataGeracao(new Date());
		
		List<Double> custoRodovias = trasnporteRodoviaDto.getRodovia().stream().filter(Objects::nonNull).map(obj -> obj.getDistancia() * obj.getTipoRodovia().getCusto()).collect(Collectors.toList());
		List<Double> custoCargaRodovias = null;
		if(trasnporteRodoviaDto.getTransporte().getCarga() > 5){
			Integer cargaRodovia = trasnporteRodoviaDto.getTransporte().getCarga() - 5;
			
			Double cargaTaxaRodoviaPeso = cargaRodovia * 0.02;
			
			custoCargaRodovias = trasnporteRodoviaDto.getRodovia().stream().map(obj -> obj.getDistancia() * cargaTaxaRodoviaPeso).collect(Collectors.toList());
			
		}
		
		Double custo = custoRodovias.stream().collect(Collectors.summingDouble(Double::doubleValue));

		if(custo == null)
			throw new BusinessException("Custo inválido.");


		custo *= trasnporteRodoviaDto.getTransporte().getVeiculo().getTipoVeiculo().getCusto();

		if(custoCargaRodovias != null && !custoCargaRodovias.isEmpty()) {
			Double custoPeso = custoCargaRodovias.stream().collect(Collectors.summingDouble(Double::doubleValue));
			custo += custoPeso;
		}

		trasnporteRodoviaDto.getTransporte().setCusto(custo);
		Transporte transpote = transporteRepository.saveAndFlush(trasnporteRodoviaDto.getTransporte());

		List<TransporteRodovia> transporteRodovias = new ArrayList<>();
		
		trasnporteRodoviaDto.getRodovia().forEach(obj ->{
			TransporteRodovia transporteRodovia = new TransporteRodovia();
		
			transporteRodovia.setTransporte(transpote);
			transporteRodovia.setRodovia(obj);
			transporteRodovia.setDistancia(obj.getDistancia());
			transporteRodovias.add(transporteRodovia);
		});
				
		return transporteRodoviaRepository.save(transporteRodovias);
	}
	
	public Page<TransporteRodoviaDto> list(Pageable pageable)
	{
		Page<Transporte> transporteList = transporteRepository.findAll(pageable);
		List<TransporteRodoviaDto> transporteRodoviaDtoList = new ArrayList<>();
		TransporteRodoviaDto transporteRodoviaDto = null;
		if(transporteList != null && transporteList.getContent() != null && !transporteList.getContent().isEmpty()) {
			
			for (Transporte obj : transporteList.getContent()) {
					
				List<TransporteRodovia> transporteRodoviaList = transporteRodoviaRepository.findByTransporteId(obj.getId());
	
				List<Integer> distanciaRodoviaPavimentada = transporteRodoviaList.stream().filter(objPavimentada -> objPavimentada != null && objPavimentada.getRodovia().getTipoRodovia().getDescricao().equals("Pavimentada")).map(TransporteRodovia::getDistancia).collect(Collectors.toList());
				
				List<Integer> distanciaRodoviaNaoPavimentada = transporteRodoviaList.stream().filter(objPavimentada -> objPavimentada != null && objPavimentada.getRodovia().getTipoRodovia().getDescricao().equals("Não-pavimentada")).map(TransporteRodovia::getDistancia).collect(Collectors.toList());
				
				 transporteRodoviaDto = new TransporteRodoviaDto();
				 
				 transporteRodoviaDto.setTransporte(obj);
				 
				 transporteRodoviaDto.setCusto(obj.getCusto());
				 
				 transporteRodoviaDto.setDistanciaPavimentada(distanciaRodoviaPavimentada.stream().flatMapToInt(IntStream::of).sum());
				 
				 transporteRodoviaDto.setDistanciaNaoPavimentada(distanciaRodoviaNaoPavimentada.stream().flatMapToInt(IntStream::of).sum());
				 
				 transporteRodoviaDtoList.add(transporteRodoviaDto);
			}
			
		}
		
		return new PageImpl<>(new ArrayList<>(transporteRodoviaDtoList), pageable, transporteList.getTotalElements());
		
	}
	
	public Page<TransporteRodoviaDto> findByDescricaoContaining(String descricao, Pageable pageable)
	{
		Page<Transporte> transporteList = transporteRepository.findByDescricao(descricao, pageable);
		List<TransporteRodoviaDto> transporteRodoviaDtoList = new ArrayList<>();
		TransporteRodoviaDto transporteRodoviaDto = null;
		if(transporteList != null && transporteList.getContent() != null && !transporteList.getContent().isEmpty()) {
			
			for (Transporte obj : transporteList.getContent()) {
					
				List<TransporteRodovia> transporteRodoviaList = transporteRodoviaRepository.findByTransporteId(obj.getId());

				List<Integer> distanciaRodoviaPavimentada = transporteRodoviaList.stream().filter(objPavimentada -> objPavimentada != null && objPavimentada.getRodovia().getTipoRodovia().getDescricao().equals("Pavimentada")).map(TransporteRodovia::getDistancia).collect(Collectors.toList());

				List<Integer> distanciaRodoviaNaoPavimentada = transporteRodoviaList.stream().filter(objPavimentada -> objPavimentada != null && objPavimentada.getRodovia().getTipoRodovia().getDescricao().equals("Não-pavimentada")).map(TransporteRodovia::getDistancia).collect(Collectors.toList());
				
				 transporteRodoviaDto = new TransporteRodoviaDto();
				 
				 transporteRodoviaDto.setTransporte(obj);
				 
				 transporteRodoviaDto.setCusto(obj.getCusto());
				 
				 transporteRodoviaDto.setDistanciaPavimentada(distanciaRodoviaPavimentada.stream().flatMapToInt(IntStream::of).sum());
				 
				 transporteRodoviaDto.setDistanciaNaoPavimentada(distanciaRodoviaNaoPavimentada.stream().flatMapToInt(IntStream::of).sum());
				 
				 transporteRodoviaDtoList.add(transporteRodoviaDto);
			}
			
		}
		
		return new PageImpl<>(new ArrayList<>(transporteRodoviaDtoList), pageable, transporteList.getTotalElements());
		
	}
}
