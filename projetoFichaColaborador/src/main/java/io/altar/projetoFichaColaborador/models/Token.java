package io.altar.projetoFichaColaborador.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity

@NamedQueries({@NamedQuery(name = "GET_TOKEN_BY_VALUE", query = "SELECT t FROM Token t WHERE t.value = :value"),
		@NamedQuery(name = "GET_TOKEN_BY_EMAIL", query = "SELECT t FROM Token t WHERE t.employeeEmail = :employeeEmail"),
		@NamedQuery(name = "GET_ID_BY_TOKEN", query = "SELECT t.id FROM Token t WHERE t.value = :value")})


public class Token extends Entity_ {
	
	private static final long serialVersionUID = 1L;
	public static final String GET_TOKEN_BY_VALUE = "GET_TOKEN_BY_VALUE";
	public static final String GET_TOKEN_BY_EMAIL = "GET_TOKEN_BY_EMAIL";
	public static final String GET_ID_BY_TOKEN = "GET_ID_BY_TOKEN";
	
	private String value;
	private Long timeToLive;
	private String employeeEmail;
	
	public Token() {}
	
	public Token(String value, String employeeEmail, Long timeToLive) {
		this.value = value;
		this.timeToLive = timeToLive;
		this.employeeEmail = employeeEmail;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(Long timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	@Override
	public String toString() {
		return "Token [value=" + value + ", timeToLive=" + timeToLive + ", employeeEmail=" + employeeEmail + ", id="
				+ id + "]";
	}

	

	
	
}
