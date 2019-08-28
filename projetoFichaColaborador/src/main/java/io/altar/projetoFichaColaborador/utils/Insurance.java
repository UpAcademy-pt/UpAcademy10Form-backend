package io.altar.projetoFichaColaborador.utils;

import javax.persistence.Entity;

import io.altar.projetoFichaColaborador.models.Entity_;

@Entity
public class Insurance extends Entity_ {

	private static final long serialVersionUID = 1L;
	private String insuranceRelativeName;
	private long insuranceRelativeBirthDate;

	public Insurance() {
	}

	public String getInsuranceRelativeName() {
		return insuranceRelativeName;
	}

	public void setInsuranceRelativeName(String insuranceRelativeName) {
		this.insuranceRelativeName = insuranceRelativeName;
	}

	public long getInsuranceRelativeBirthDate() {
		return insuranceRelativeBirthDate;
	}

	public void setInsuranceRelativeBirthDate(long insuranceRelativeBirthDate) {
		this.insuranceRelativeBirthDate = insuranceRelativeBirthDate;
	}
}
