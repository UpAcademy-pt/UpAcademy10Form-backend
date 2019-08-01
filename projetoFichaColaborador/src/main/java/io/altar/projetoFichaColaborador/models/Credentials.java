package io.altar.projetoFichaColaborador.models;

import java.io.Serializable;

public class Credentials implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private int password;
	private String role;

	public Credentials() {
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
