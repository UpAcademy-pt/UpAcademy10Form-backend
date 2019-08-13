package io.altar.projetoFichaColaborador.models;

import java.io.Serializable;

public class Credentials implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String username;
	private String password;
	private String role;

	public Credentials() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
