package com.sienge.transporte.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Rodovia {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String nome;
	
	
    @ManyToOne
    @JoinColumn(name = "tipo_rodovia_id")
	TipoRodovia tipoRodovia;
    
    @Transient @Getter @Setter
	Integer distancia;
    
	public Rodovia(Long id, String nome, TipoRodovia tipoRodovia) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipoRodovia = tipoRodovia;
	}
	
	public Rodovia(Long id, String nome, TipoRodovia tipoRodovia, Integer distancia) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipoRodovia = tipoRodovia;
		this.distancia = distancia;
		
	}

    
    

	
}
