package io.altar.projetoFichaColaborador.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "GET_ALL_USERS", query = "SELECT u FROM User u"),
		@NamedQuery(name = "GET_USER_LOGIN", query = "SELECT u  FROM User u WHERE EXISTS(SELECT u.id  FROM User u WHERE u.username =:username AND u.password =:password)") })

public class User extends Entity_ {

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_USERS = "GET_ALL_USERS";
	public static final String GET_USER_LOGIN = "GET_USER_LOGIN";

	private String username;
	private int password;
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
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
