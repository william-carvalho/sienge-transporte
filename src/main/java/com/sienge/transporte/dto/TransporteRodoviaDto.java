package com.sienge.transporte.dto;


import java.util.Set;



import com.sienge.transporte.domain.Rodovia;
import com.sienge.transporte.domain.Transporte;




public class TransporteRodoviaDto {


	Long id; 

    private Transporte transporte;

    private Set<Rodovia> rodovia;
    
    private transient Integer distanciaPavimentada;
    
    private transient Integer distanciaNaoPavimentada;
    
    private transient Integer carga;
    
    private transient Double custo;

	public TransporteRodoviaDto() {
		super();
	}

	public TransporteRodoviaDto(Long id, Transporte transporte, Set<Rodovia> rodovia) {
		super();
		this.id = id;
		this.transporte = transporte;
		this.rodovia = rodovia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Transporte getTransporte() {
		return transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	public Set<Rodovia> getRodovia() {
		return rodovia;
	}

	public void setRodovia(Set<Rodovia> rodovia) {
		this.rodovia = rodovia;
	}
	
	

	public Integer getDistanciaPavimentada() {
		return distanciaPavimentada;
	}

	public void setDistanciaPavimentada(Integer distanciaPavimentada) {
		this.distanciaPavimentada = distanciaPavimentada;
	}

	public Integer getDistanciaNaoPavimentada() {
		return distanciaNaoPavimentada;
	}

	public void setDistanciaNaoPavimentada(Integer distanciaNaoPavimentada) {
		this.distanciaNaoPavimentada = distanciaNaoPavimentada;
	}

	public Integer getCarga() {
		return carga;
	}

	public void setCarga(Integer carga) {
		this.carga = carga;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rodovia == null) ? 0 : rodovia.hashCode());
		result = prime * result + ((transporte == null) ? 0 : transporte.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransporteRodoviaDto other = (TransporteRodoviaDto) obj;
		if (id == null) {
			return other.id == null;
		} 
		return true;
	}

	@Override
	public String toString() {
		return "TransporteRodoviaDto [id=" + id + ", transporte=" + transporte + "]";
	}





}
