package com.sienge.transporte.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sienge.transporte.domain.Rodovia;
import com.sienge.transporte.domain.TipoRodovia;
import com.sienge.transporte.domain.TipoVeiculo;
import com.sienge.transporte.domain.Transporte;
import com.sienge.transporte.domain.Veiculo;
import com.sienge.transporte.dto.TransporteRodoviaDto;
import com.sienge.transporte.repository.RodoviaRepository;
import com.sienge.transporte.repository.TipoRodoviaRepository;
import com.sienge.transporte.repository.TipoVeiculoRepository;
import com.sienge.transporte.repository.VeiculoRepository;
import com.sienge.transporte.service.TransporteRodoviaService;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	TipoVeiculoRepository tipoVeiculoRepository;
	
	@Autowired
	TipoRodoviaRepository tipoRodoviaRepository;
	
	@Autowired
	RodoviaRepository rodoviaRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired
	TransporteRodoviaService transporteRodoviaService;
	

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		tipoVeiculoRepository.save(new TipoVeiculo(1L, "Caminhão  baú", 1.00 ));
		tipoVeiculoRepository.save(new TipoVeiculo(2L, "Caminhão  caçamba", 1.05 ));
		tipoVeiculoRepository.save(new TipoVeiculo(3L, "Carreta", 1.12 ));
		
		tipoRodoviaRepository.save(new TipoRodovia(1L, "Pavimentada", 0.54 ));
		tipoRodoviaRepository.save(new TipoRodovia(2L, "Não-pavimentada", 0.62 ));
		
		Rodovia rodovia = rodoviaRepository.save(new Rodovia(1L, "BR-101", tipoRodoviaRepository.findOne(1L), 100));
		Rodovia rodovia4 = rodoviaRepository.save(new Rodovia(2L, "BR-282", tipoRodoviaRepository.findOne(1L)));
		Rodovia rodovia6 = rodoviaRepository.save(new Rodovia(3L, "SC-470", tipoRodoviaRepository.findOne(1L)));
		rodoviaRepository.save(new Rodovia(4L, "BR-319", tipoRodoviaRepository.findOne(1L)));
		rodoviaRepository.save(new Rodovia(5L, "SP-510", tipoRodoviaRepository.findOne(1L)));
		rodoviaRepository.save(new Rodovia(5L, "SP-1133", tipoRodoviaRepository.findOne(2L)));
		rodoviaRepository.save(new Rodovia(6L, "SP-5434", tipoRodoviaRepository.findOne(2L)));
		rodoviaRepository.save(new Rodovia(7L, "RS-3232", tipoRodoviaRepository.findOne(2L)));
		rodoviaRepository.save(new Rodovia(8L, "PR-5230", tipoRodoviaRepository.findOne(2L)));
		Rodovia rodovia2 = rodoviaRepository.save(new Rodovia(9L, "AC-5101", tipoRodoviaRepository.findOne(2L)));
		Rodovia rodovia3 = rodoviaRepository.save(new Rodovia(10L, "GO-7680", tipoRodoviaRepository.findOne(2L)));
		Rodovia rodovia5 = rodoviaRepository.save(new Rodovia(11L, "DF-1577", tipoRodoviaRepository.findOne(2L)));
		Rodovia rodovia7 = rodoviaRepository.save(new Rodovia(12L, "MT-9988", tipoRodoviaRepository.findOne(2L)));
		rodoviaRepository.save(new Rodovia(13L, "TO-4542", tipoRodoviaRepository.findOne(2L)));
		
		
		Veiculo veiculo2 = veiculoRepository.save(new Veiculo(1L, "DAILY CHASSI 35.10/ 35.13/ 40.13", "IVECO","QQQ 1234", "VERMELHO", "2010", "1213155DFDAAS", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		Veiculo veiculo4 = veiculoRepository.save(new Veiculo(2L, "R-114 GA 380 6x2 NZ/4x2 3-Eixos", "SCANIA","QTQ 3523", "AZUL", "2012", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(3L, "1114 3-Eixos", "MERCEDES-BENZ","QTH 1523", "BRANCO", "2002", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(4L, "CARGO 2422/ 2422 E 3-Eixos", "FORD","QTG 3555", "VERDE", "2014", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(5L, "FH-460 GLOBETROTTER 6x4 (E5)", "VOLVO","QYU 3532", "AMARELO", "2015", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(6L, "190-T", "FIAT","QRR 3532", "PRETO", "2012", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(7L, "AUMARK 6.50AK 3.8 4x2 TB(E5)", "FOTON","RTH 1235", "PRETO", "2013", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(8L, "L-111", "SCANIA","JIJ 3678", "BRANCO", "2011", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(9L, "R-114 GA 380 6x2 NZ/4x2 3-Eixos", "SCANIA","BRR 3523", "BRANCO", "2010", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(1L)));
		veiculoRepository.save(new Veiculo(10L, "R-440 A 6x4", "SCANIA","BYU 5432", "BRANCO", "2009", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(3L)));
		Veiculo veiculo5 = veiculoRepository.save(new Veiculo(11L, "AA1", "SAAB-SCANIA","NIN 6676", "BEGE", "2012", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(2L)));
		Veiculo veiculo3 = veiculoRepository.save(new Veiculo(12L, "BB2", "SAAB-SCANIA","JJI 5432", "BORDO", "2012", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(3L)));
		veiculoRepository.save(new Veiculo(13L, "CCC3", "SAAB-SCANIA","UIU 8767", "PURPURA", "2012", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(2L)));
		veiculoRepository.save(new Veiculo(14L, "R888", "SAAB-SCANIA","BVB 4356", "JUMBO", "2012", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(2L)));
		Veiculo veiculo = veiculoRepository.save(new Veiculo(15L, "V1000", "SAAB-SCANIA","HGG 4321", "METALICO", "2012", "4343432FDF343", "TRUCK", tipoVeiculoRepository.findOne(2L)));
		
		Transporte transporte = new Transporte();
		
		transporte.setDescricao("Batata doce");
		
		transporte.setCarga(8);
		
		transporte.setVeiculo(veiculo);
		
		TransporteRodoviaDto transporteRodoviaDto = new TransporteRodoviaDto();
		
		transporteRodoviaDto.setTransporte(transporte);
		
		Set<Rodovia> set1 = new HashSet<>();
		rodovia.setDistancia(100);
		set1.add(rodovia);
		transporteRodoviaDto.setRodovia(set1);
		
		transporteRodoviaService.create(transporteRodoviaDto);
		
		Transporte transporte2 = new Transporte();
		
		transporte2.setDescricao("Feijão");
		
		transporte2.setCarga(4);
		
		transporte2.setVeiculo(veiculo2);
		
		TransporteRodoviaDto transporteRodoviaDto2 = new TransporteRodoviaDto();
		
		transporteRodoviaDto2.setTransporte(transporte2);
		
		Set<Rodovia> set2 = new HashSet<>();
		rodovia2.setDistancia(60);
		set2.add(rodovia2);
		transporteRodoviaDto2.setRodovia(set2);
		
		transporteRodoviaService.create(transporteRodoviaDto2);
		
		Transporte transporte3 = new Transporte();
		
		transporte3.setDescricao("Arroz");
		
		transporte3.setCarga(12);
		
		transporte3.setVeiculo(veiculo3);
		
		TransporteRodoviaDto transporteRodoviaDto3 = new TransporteRodoviaDto();
		
		transporteRodoviaDto3.setTransporte(transporte3);
		
		Set<Rodovia> set3 = new HashSet<>();
		rodovia3.setDistancia(180);
		set3.add(rodovia3);
		transporteRodoviaDto3.setRodovia(set3);
		
		transporteRodoviaService.create(transporteRodoviaDto3);
		
	    Transporte transporte4 = new Transporte();
		
		transporte4.setDescricao("Milho");
		
		transporte4.setCarga(6);
		
		transporte4.setVeiculo(veiculo4);
		
		TransporteRodoviaDto transporteRodoviaDto4 = new TransporteRodoviaDto();
		
		transporteRodoviaDto4.setTransporte(transporte4);
		
		Set<Rodovia> set4 = new HashSet<>();
		rodovia4.setDistancia(80);
		set4.add(rodovia4);
		rodovia5.setDistancia(20);
		set4.add(rodovia5);
		transporteRodoviaDto4.setRodovia(set4);
		
		transporteRodoviaService.create(transporteRodoviaDto4);
		
		
		
	    Transporte transporte5 = new Transporte();
		
		transporte5.setDescricao("Soja");
		
		transporte5.setCarga(5);
		
		transporte5.setVeiculo(veiculo5);
		
		TransporteRodoviaDto transporteRodoviaDto5 = new TransporteRodoviaDto();
		
		transporteRodoviaDto5.setTransporte(transporte5);
		
		Set<Rodovia> set5 = new HashSet<>();
		rodovia6.setDistancia(50);
		set5.add(rodovia6);
		rodovia7.setDistancia(30);
		set5.add(rodovia7);
		transporteRodoviaDto5.setRodovia(set5);
		
		transporteRodoviaService.create(transporteRodoviaDto5);
		
		

		
	}
}
