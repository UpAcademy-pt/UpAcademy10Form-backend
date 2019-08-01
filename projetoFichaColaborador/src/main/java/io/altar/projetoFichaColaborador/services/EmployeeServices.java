package io.altar.projetoFichaColaborador.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import io.altar.projetoFichaColaborador.business.EmployeeBusiness;

@Path("employee")
public class EmployeeServices {
	
	@Context
	private UriInfo context;

	@Inject
	private EmployeeBusiness eb;

	@GET
	@Path("isOk")
	@Produces(MediaType.TEXT_PLAIN)
	public String healthCheck() {
		return "URI " + context.getRequestUri().toString() + " is OK!";
	}

}
