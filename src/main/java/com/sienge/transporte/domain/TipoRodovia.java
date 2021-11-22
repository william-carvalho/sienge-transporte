package com.sienge.transporte.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class TipoRodovia {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String descricao;
	Double custo;
	
	public TipoRodovia(Long id) {
		super();
		this.id = id;
	}
	
}
