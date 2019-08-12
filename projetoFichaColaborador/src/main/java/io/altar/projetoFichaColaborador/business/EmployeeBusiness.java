package io.altar.projetoFichaColaborador.business;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.models.Filters;
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
		boolean valida = emR.countEmployeeExistsByEntity(employee);
		if (valida) {
			employee.setModified(Instant.now().toEpochMilli());
			eR.update(employee);
			return Response.status(Response.Status.OK).entity(employee).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Esta ficha nao existe").build();
	}

	public Response getEmployeeById(long id) {
		boolean valid = emR.countEmployeeExistsById(id);
		if (valid) {
			Employee employee = eR.getEntityById(id);
			return Response.ok(employee, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
	}

	public Response getAllEmployees() {
		List<Employee> tempAllEmployees = eR.getAll();
		if (tempAllEmployees.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
		}
		return Response.ok(tempAllEmployees, MediaType.APPLICATION_JSON).build();
	}

	public Response removeEmployee(long id) {
		boolean valid = emR.countEmployeeExistsById(id);
		if (valid) {
			Employee employee = eR.getEntityById(id);
			eR.remove(id);
			return Response.ok(employee, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
	}

	public Response filterEmployeesValidation(Filters filter) {
		List<Employee> filteredEmployeesList = emR.filterEmployees(filter);
		if (filteredEmployeesList.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Não há resultados que correspondam à sua pesquisa").build();
		}
		return Response.ok(filteredEmployeesList, MediaType.APPLICATION_JSON).build();
	}
}
