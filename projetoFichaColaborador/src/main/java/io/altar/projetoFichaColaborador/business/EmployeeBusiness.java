package io.altar.projetoFichaColaborador.business;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.models.Filters;
import io.altar.projetoFichaColaborador.repositories.EmployeeRepository;
import io.altar.projetoFichaColaborador.utils.MultiReturn;

public class EmployeeBusiness {

	@Inject
	private EmployeeRepository emR;


	public void createEmployee(Employee employee) {
		emR.create(employee);
	}

	public Response updateEmployee(Employee employee) {
		boolean valida = emR.checkEmployeeExistsByEntity(employee);
		if (valida) {
			employee.setModified(Instant.now().toEpochMilli());
			emR.update(employee);
			return Response.status(Response.Status.OK).entity(employee).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Esta ficha nao existe").build();
	}
	
	public Response removeEmployee(long id) {
		boolean valid = emR.checkEntityExistsById(id);
		if (valid) {
			Employee employee = emR.getEntityById(id);
			emR.remove(id);
			return Response.ok(employee, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
	}

	public Response getEmployeeById(long id) {
		boolean valid = emR.checkEntityExistsById(id);
		if (valid) {
			Employee employee = emR.getEntityById(id);
			return Response.ok(employee, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
	}

	public Response getAllEmployees() {
		List<Employee> tempAllEmployees = emR.getAll();
		if (tempAllEmployees.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
		}
		return Response.ok(tempAllEmployees, MediaType.APPLICATION_JSON).build();
	}

	public Response filterEmployeesValidation(Filters filter) {
		long countFilteredEmployeesList = filter.getCountResults();
		if (!filter.getNextPage()) {
			countFilteredEmployeesList = emR.countFilterEmployees(filter);
		}
		List<Employee> filteredEmployeesList = emR.filterEmployees(filter);
		
		MultiReturn<Employee> mR = new MultiReturn<Employee>(filteredEmployeesList, countFilteredEmployeesList);
		
		if (filteredEmployeesList.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Não há resultados que correspondam à sua pesquisa").build();
		}
		return Response.ok(mR, MediaType.APPLICATION_JSON).build();
	}
}
