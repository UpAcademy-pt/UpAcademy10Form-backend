package io.altar.projetoFichaColaborador.business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Employee;
import io.altar.projetoFichaColaborador.models.Filters;
import io.altar.projetoFichaColaborador.repositories.EmployeeRepository;
import io.altar.projetoFichaColaborador.utils.MultiReturn;

public class EmployeeBusiness {

	@Inject
	private EmployeeRepository emR;

	@Transactional
	public void createEmployee(Employee employee) {
		emR.create(employee);
	}

	@Transactional
	public Response updateEmployee(Employee employee) {
		if (emR.checkEmployeeExistsByEntity(employee)) {
			emR.update(employee);
			return Response.status(Response.Status.OK).entity(employee).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Esta ficha nao existe").build();
	}

	@Transactional
	public Response removeEmployee(long id) {
		if (emR.checkEntityExistsById(id)) {
			emR.remove(id);
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
	}

	public Response getEmployeeById(long id) {
		if (emR.checkEntityExistsById(id)) {
			return Response.ok(emR.getEntityById(id), MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Essa ficha nao existe").build();
	}

	public Response getAllEmployees() {
		if (emR.getAll().isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Essas fichas nao existem").build();
		}
		return Response.ok(emR.getAll(), MediaType.APPLICATION_JSON).build();
	}

	@Transactional
	public Response filterEmployeesValidation(Filters filter) {
		long countFilteredEmployeesList = emR.countFilterEmployees(filter);
		if (filter.getAdmissionDateMIN() == null && filter.getClient() == null && filter.getDistrict() == null
				&& filter.getProfessionalCategory() == null && filter.getSpecialTech() == null) {
			if (countFilteredEmployeesList != 0) {
				return Response.ok(emR.getAll(), MediaType.APPLICATION_JSON).build();
			}
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Não há resultados que correspondam à sua pesquisa").build();
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
