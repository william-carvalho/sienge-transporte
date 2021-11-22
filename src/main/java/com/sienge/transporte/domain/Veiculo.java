package com.sienge.transporte.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Veiculo {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String nome;
	String marca;
	String placa;
	String cor;
	String ano;
	String chassi;
	String fabricante;
	
    @ManyToOne
    @JoinColumn(name = "tipo_veiculo_id")
	TipoVeiculo tipoVeiculo;
	
}
