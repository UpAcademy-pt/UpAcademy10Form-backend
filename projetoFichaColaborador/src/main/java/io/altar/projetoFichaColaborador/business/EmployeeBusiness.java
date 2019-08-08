package io.altar.projetoFichaColaborador.business;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.repositories.EmployeeRepository;
import io.altar.projetoFichaColaborador.repositories.EntityRepository;

public class EmployeeBusiness {

	@Inject
	private EntityRepository<Employee> eR;
	
	@Inject
	private EmployeeRepository emR;
	
	public void createEmployee(Employee employee) {
		eR.create(employee);
	}

	public Response updateEmployee(Employee employee) {
		boolean valida = emR.countEmployeeExists(employee);
		if (valida) {
			employee.setModified(Instant.now());
			eR.update(employee);
			return Response.status(Response.Status.OK).entity(employee).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Este ficha nao existe").build();
		}
	}

	public Response getEmployeeById(long id) {
		Employee employee = eR.getEntityById(id);
		if (employee.getId() > 0) {
			return Response.accepted().entity(employee).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Este colaborador nao existe").build();
		}
	}

	public Response getAllEmployees() {
		List<Employee> tempAllEmployees = eR.getAll();
		if (tempAllEmployees != null) {
			return Response.accepted().entity(tempAllEmployees).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
	
	public Response removeEmployee(long id) {
		Employee employee = eR.getEntityById(id);
		//boolean valida = emR.countEmployeeExists(employee);
		if (employee!=null) {
			eR.remove(id);
			return Response.status(Response.Status.OK).entity(employee).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Esta ficha nao existe").build();
		}

	}
}
