package io.altar.projetoFichaColaborador.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import io.altar.projetoFichaColaborador.utils.Insurance;

@Entity
@NamedQueries({
		@NamedQuery(name = "GET_ALL_EMPLOYEES", query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.insuranceDetails"),
		@NamedQuery(name = "GET_EMPLOYEE_BY_ID", query = "SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.insuranceDetails WHERE e.id =:entityId"),
		@NamedQuery(name = "CHECK_EMPLOYEE_EXISTS_BY_ID", query = "SELECT COUNT(e) FROM Employee e WHERE e.id =:entityId") })

public class Employee extends Entity_ {

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_EMPLOYEES = "GET_ALL_EMPLOYEES";
	public static final String GET_EMPLOYEE_BY_ID = "GET_EMPLOYEE_BY_ID";
	public static final String CHECK_EMPLOYEE_EXISTS_BY_ID = "CHECK_EMPLOYEE_EXISTS_BY_ID";

	private String name;
	private String address;
	private String postalCode;
	private String location;
	private String district;
	private String email;
	private String phoneNumber;
	private String academicQualifications;
	private String academicInstitution;
	private String course;
	private int cc;
	private long ccValidity;
	private String nationality;
	private long birthDate;
	private String nif;
	private String niss;
	private String maritalStatus;
	private boolean maritalWorkStatus;
	private boolean dependents;
	private int dependentsNumber;
	private int[] dependentsAges = new int[5];
	private String iban;
	private String swift;
	private String professionalCategory;
	private String specialTech;
	private ArrayList<String> otherTech;
	private long admissionDate;
	private float grossSalary;
	private String contractType;
	private ArrayList<String> bonus;
	private boolean twelfths;
	private String twelfthsType;
	private String workSchedule = "9h-18h";
	private boolean sports;
	private String sportsType;
	private String otherActivities;
	private String suggestedActivities;
	private String hobbies;
	private String otherInterests;
	private String client;
	private String clientSector;
	private String project;
	private String fare;
	private String commentarySection;
	private boolean healthInsurance;
	private boolean companyFinancing;
	private boolean extensible;
	private int companyFinancingRelative;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private List<Insurance> insuranceDetails;

	public Employee() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAcademicQualifications() {
		return academicQualifications;
	}

	public void setAcademicQualifications(String academicQualifications) {
		this.academicQualifications = academicQualifications;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public long getCcValidity() {
		return ccValidity;
	}

	public void setCcValidity(long ccValidity) {
		this.ccValidity = ccValidity;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public long getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(long birthDate) {
		this.birthDate = birthDate;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNiss() {
		return niss;
	}

	public void setNiss(String niss) {
		this.niss = niss;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public boolean getMaritalWorkStatus() {
		return maritalWorkStatus;
	}

	public void setMaritalWorkStatus(boolean maritalWorkStatus) {
		this.maritalWorkStatus = maritalWorkStatus;
	}

	public boolean getDependents() {
		return dependents;
	}

	public void setDependents(boolean dependents) {
		this.dependents = dependents;
	}

	public int[] getDependentsAges() {
		return dependentsAges;
	}

	public void setDependentsAges(int[] dependentAges) {
		this.dependentsAges = dependentAges;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getProfessionalCategory() {
		return professionalCategory;
	}

	public void setProfessionalCategory(String professionalCategory) {
		this.professionalCategory = professionalCategory;
	}

	public long getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(long admissionDate) {
		this.admissionDate = admissionDate;
	}

	public float getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(float grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public ArrayList<String> getBonus() {
		return bonus;
	}

	public void setBonus(ArrayList<String> bonus) {
		this.bonus = bonus;
	}

	public boolean getTwelfths() {
		return twelfths;
	}

	public void setTwelfths(boolean twelfths) {
		this.twelfths = twelfths;
	}

	public String getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(String workTine) {
		this.workSchedule = workTine;
	}

	public boolean getSports() {
		return sports;
	}

	public void setSports(boolean sports) {
		this.sports = sports;
	}

	public String getSportsType() {
		return sportsType;
	}

	public void setSportsType(String sportsType) {
		this.sportsType = sportsType;
	}

	public String getOtherActivities() {
		return otherActivities;
	}

	public void setOtherActivities(String otherActivities) {
		this.otherActivities = otherActivities;
	}

	public String getSuggestedActivities() {
		return suggestedActivities;
	}

	public void setSuggestedActivities(String suggestedActivities) {
		this.suggestedActivities = suggestedActivities;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getOtherInterests() {
		return otherInterests;
	}

	public void setOtherInterests(String otherInterests) {
		this.otherInterests = otherInterests;
	}

	public String getAcademicInstitution() {
		return academicInstitution;
	}

	public void setAcademicInstitution(String academicInstitution) {
		this.academicInstitution = academicInstitution;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSpecialTech() {
		return specialTech;
	}

	public void setSpecialTech(String specialTech) {
		this.specialTech = specialTech;
	}

	public ArrayList<String> getOtherTech() {
		return otherTech;
	}

	public void setOtherTech(ArrayList<String> otherTech) {
		this.otherTech = otherTech;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientSector() {
		return clientSector;
	}

	public void setClientSector(String clientSector) {
		this.clientSector = clientSector;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public boolean getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(boolean healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getCommentarySection() {
		return commentarySection;
	}

	public void setCommentarySection(String commentarySection) {
		this.commentarySection = commentarySection;
	}

	public boolean getCompanyFinancing() {
		return companyFinancing;
	}

	public void setCompanyFinancing(boolean companyFinancing) {
		this.companyFinancing = companyFinancing;
	}

	public boolean getExtensible() {
		return extensible;
	}

	public void setExtensible(boolean extensible) {
		this.extensible = extensible;
	}

	public int getCompanyFinancingRelative() {
		return companyFinancingRelative;
	}

	public void setCompanyFinancingRelative(int companyFinancingRelative) {
		this.companyFinancingRelative = companyFinancingRelative;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTwelfthsType() {
		return twelfthsType;
	}

	public void setTwelfthsType(String twelfthsType) {
		this.twelfthsType = twelfthsType;
	}

	public int getDependentsNumber() {
		return dependentsNumber;
	}

	public void setDependentsNumber(int dependentsNumber) {
		this.dependentsNumber = dependentsNumber;
	}

	public List<Insurance> getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(List<Insurance> insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

}
