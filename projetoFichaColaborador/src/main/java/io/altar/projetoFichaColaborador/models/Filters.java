package io.altar.projetoFichaColaborador.models;

import java.io.Serializable;

public class Filters implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long admissionDateMIN;
	private Long admissionDateMAX;
	private String specialTech;
	private String district;


	public Filters(long admissionDateMIN, long admissionDateMAX, String specialTech, String district, int maxResultsInQuery) {
		this.admissionDateMIN = admissionDateMIN;
		this.admissionDateMAX = admissionDateMAX;
		this.specialTech = specialTech;
		this.district = district;

	}

	public Filters() {
	}

	public Long getAdmissionDateMIN() {
		return admissionDateMIN;
	}

	public void setAdmissionDateMIN(Long admissionDateMIN) {
		this.admissionDateMIN = admissionDateMIN;
	}

	public Long getAdmissionDateMAX() {
		return admissionDateMAX;
	}

	public void setAdmissionDateMAX(Long admissionDateMAX) {
		this.admissionDateMAX = admissionDateMAX;
	}

	public String getSpecialTech() {
		return specialTech;
	}

	public void setSpecialTech(String specialTech) {
		this.specialTech = specialTech;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
}
