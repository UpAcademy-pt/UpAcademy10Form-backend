package io.altar.projetoFichaColaborador.business;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.repositories.EntityRepository;


public class EmployeeBusiness {
	
	@Inject
	private EntityRepository<Employee> eR;
	
	
	public Response getAllEmployees() {
		
		List<Employee> tempAllEmployees = eR.getAll();
		if (tempAllEmployees != null) {
			return Response.accepted().entity(tempAllEmployees).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
	}
	
	
	public Response getEmpById(long id) {
		
		Employee employee = eR.getEntityById(id);
		if (employee.getId() != null) {
			return Response.accepted().entity(employee).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Este colaborador nao existe").build();
		}
		
	}
	
	
	public void createEmployee(Employee employee) {
		
		eR.create(employee);
		
	}
	
	
	
	

}
