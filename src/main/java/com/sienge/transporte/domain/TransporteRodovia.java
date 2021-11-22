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
public class TransporteRodovia {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id; 
	
	@ManyToOne
    @JoinColumn(name = "transporte_id")
    private Transporte transporte;

	@ManyToOne
    @JoinColumn(name = "rodovia_id")
    private Rodovia rodovia;

	private Integer distancia;
    


}
