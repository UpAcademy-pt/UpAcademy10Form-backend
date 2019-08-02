package io.altar.projetoFichaColaborador.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.projetoFichaColaborador.business.EmployeeBusiness;
import io.altar.projetoFichaColaborador.models.Employee;

 
@Path("employee")
public class EmployeeServices {
	
	@Context
	private UriInfo context;

	
	@Inject
	private EmployeeBusiness eB;

	
	@GET
	@Path("isOk")
	@Produces(MediaType.TEXT_PLAIN)
	public String healthCheck() {
		return "URI " + context.getRequestUri().toString() + " is OK!";
	}
	
	
	@GET
	@Path("getAllEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees() {
		return eB.getAllEmployees();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getEmpById(@PathParam("id") long id) {
		
		return eB.getEmpById(id);
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createEmployee(Employee employee) {
		
		eB.createEmployee(employee);
		return Response.ok().build();
		
	}
	

}
