package io.altar.projetoFichaColaborador.repositories;

import javax.faces.bean.RequestScoped;

import io.altar.projetoFichaColaborador.models.Employee;;


@RequestScoped
public class EmployeeRepository extends EntityRepository<Employee> {

	@Override
	protected Class<Employee> getEntityClass() {
		return Employee.class;
	}

	
	// Get all entity occurrences
	@Override
	protected String getAllQuery() {
		
		return Employee.GET_ALL_EMPLOYEES;
		
	}
	
	
	// Get entity occurrence by ID
	@Override
	protected String getByIdQuery() {

		return Employee.GET_EMP_BY_ID;
	}


	@Override
	protected String countEntityExistsQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}
