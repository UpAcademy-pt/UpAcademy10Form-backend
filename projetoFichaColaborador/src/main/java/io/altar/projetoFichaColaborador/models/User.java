package io.altar.projetoFichaColaborador.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "GET_ALL_USERS", query = "SELECT u FROM User u"),
		@NamedQuery(name = "GET_USER_LOGIN", query = "SELECT u  FROM User u WHERE u.username =:username AND u.password =:password"),
		@NamedQuery(name = "GET_USER_BY_ID", query = "SELECT u  FROM User u WHERE u.id =:userId"),
		@NamedQuery(name = "COUNT_USER_EXISTS", query = "SELECT count(u)  FROM User u WHERE u.id =:userId")})

public class User extends Entity_ {

	public User() {
	}

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_USERS = "GET_ALL_USERS";
	public static final String GET_USER_LOGIN = "GET_USER_LOGIN";
	public static final String GET_USER_BY_ID = "GET_USER_BY_ID";
	public static final String COUNT_USER_EXISTS = "COUNT_USER_EXISTS";

	private String username;
	private String password;
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
