package io.altar.projetoFichaColaborador.repositories;

import javax.faces.bean.RequestScoped;

import io.altar.projetoFichaColaborador.models.Employee;;


@RequestScoped
public class EmployeeRepository extends EntityRepository<Employee> {

	@Override
	protected Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	protected String getAllQuery() {
		
		return null;
	}
	
	protected String getAllReg() {
		
		return Employee.GET_ALL_EMPLOYEES;
		
	}

	@Override
	protected String getByIdQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}
