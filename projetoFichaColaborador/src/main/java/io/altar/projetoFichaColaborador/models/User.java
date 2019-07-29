package io.altar.projetoFichaColaborador.models;

import javax.persistence.Entity;

import io.altar.projetoFichaColaborador.models.Entity_;

@Entity
public class User extends Entity_{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String role ; // to do
	//private String roles[]= {"admin","owner"};
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private int phoneNumber;
	private int cellPhoneNumber;
	private String address;
	private String postalCode;
	private String iban;
	private String nif;
	private String cc;
	private String lastUpdate;
	private String birthDate;
	private boolean drivinglicense;
	
		
	public User() {}
	
}
