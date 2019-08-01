package io.altar.projetoFichaColaborador.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "GET_ALL_USERS", query = "SELECT u FROM User u"),
		@NamedQuery(name = "GET_USER_LOGIN", query = "SELECT u  FROM User u WHERE EXISTS(SELECT u.id  FROM User u WHERE u.username =:username AND u.password =:password)"),
		@NamedQuery(name = "GET_USER_BY_ID", query = "SELECT u  FROM User u WHERE u.id =:userId")})

public class User extends Entity_ {

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_USERS = "GET_ALL_USERS";
	public static final String GET_USER_LOGIN = "GET_USER_LOGIN";
	public static final String 	GET_USER_BY_ID = "GET_USER_BY_ID";

	private String username;
	private int password;
	private String role;
	private Date created;
	private Date modified;

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}
