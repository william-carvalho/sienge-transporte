package com.sienge.transporte.domain;

import java.util.Date;
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
public class Transporte {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String descricao;
	Integer carga;
	Double custo;
	Date dataGeracao;
	
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
	Veiculo veiculo;
    
    


}
