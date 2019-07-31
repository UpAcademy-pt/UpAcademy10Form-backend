package io.altar.projetoFichaColaborador.services;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.projetoFichaColaborador.business.UserBusiness;
import io.altar.projetoFichaColaborador.models.Credentials;

@Path("user")
public class UserService {

	@Context
	private UriInfo context;

	@Inject
	private UserBusiness ub;

	@GET
	@Path("isOk")
	@Produces(MediaType.TEXT_PLAIN)
	public String healthCheck() {
		return "URI " + context.getRequestUri().toString() + " is OK!";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAllUsers() {
		return ub.businessGetAllUsers();
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUserLogin(Credentials userCredential) {
		return ub.userLogin(userCredential);
	}

}
