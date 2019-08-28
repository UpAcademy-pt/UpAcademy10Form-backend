package io.altar.projetoFichaColaborador.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "myUser")
@NamedQueries({ @NamedQuery(name = "GET_ALL_USERS", query = "SELECT u FROM User u"),
		@NamedQuery(name = "GET_USER_LOGIN", query = "SELECT u  FROM User u WHERE u.username =:username AND u.password =:password"),
		@NamedQuery(name = "GET_USER_BY_ID", query = "SELECT u  FROM User u WHERE u.id =:entityId"),
		@NamedQuery(name = "CHECK_USER_EXISTS_BY_ID", query = "SELECT COUNT(u) FROM User u WHERE u.id =:entityId") })

public class User extends Entity_ {

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_USERS = "GET_ALL_USERS";
	public static final String GET_USER_BY_ID = "GET_USER_BY_ID";
	public static final String CHECK_USER_EXISTS_BY_ID = "CHECK_USER_EXISTS_BY_ID";
	public static final String GET_USER_LOGIN = "GET_USER_LOGIN";

	private String username;
	private String password;
	private String role;

	public User() {
	}

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
