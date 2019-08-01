package io.altar.projetoFichaColaborador.models;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Employee extends Entity_{
	
	private static final long serialVersionUID = 1L;
	
//	private String username;
//	private String password;
//	private String role ; // to do
//	//private String roles[]= {"admin","owner"};
	private String name;
	private String address;
	private String postalCode;
	private String location;
	private String email;
	private int phoneNumber;
	private String academicQualifications;
	private int cc;
	private Date ccValidity;
	private String nationality;
	private Date birthDate;
	private String nif;
	private String niss;
	private String maritalStatus;
	private boolean maritalWorkStatus;
	private int dependents;
	private String dependentAges;
	private String iban;
	private String swift;
	private String professionalCategory = "Consultor de sistemas de Informação";
	private Date admissionDate; //?
	private float grossSalary;
	private String contractType;
	private String extras;
	private boolean twelfths;
	private boolean twelfths5050;
	private boolean twelfths112;
	private boolean foodAllowance;
	private String workSchedule = "9h-18h";
	private boolean sports;
	private String sportsType;
	private String otherActivities;
	private String suggestedActivities;
	private String hobbies;
	private String otherInterests;
		
	public Employee() {}
	
}
