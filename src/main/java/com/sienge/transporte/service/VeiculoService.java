package com.sienge.transporte.service;

import com.sienge.transporte.domain.Veiculo;
import com.sienge.transporte.resource.VeiculoResource;
import com.sienge.transporte.resource.VeiculoResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    VeiculoService repository;

    VeiculoResourceAssembler assembler = new VeiculoResourceAssembler();

    public Page<Veiculo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Veiculo findOne(Long id) {
        return repository.findOne(id);
    }

    public VeiculoResource save(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public VeiculoResource update(Long id, Veiculo veiculo) {
        veiculo.setId(id);
        return repository.save(veiculo);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public Page<Veiculo> findByNomeContaining(String nome, Pageable pageable) {
        return repository.findByNomeContaining(nome, pageable);
    }
}
