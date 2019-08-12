package io.altar.projetoFichaColaborador.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.projetoFichaColaborador.business.UserBusiness;
import io.altar.projetoFichaColaborador.models.User;

@Path("users")
public class UserService {

	@Context
	private UriInfo context;

	@Inject
	private UserBusiness uB;

	@GET
	@Path("isOk")
	@Produces(MediaType.TEXT_PLAIN)
	public String healthCheck() {
		return "URI " + context.getRequestUri().toString() + " is OK!";
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") long id) {
		return uB.getUserById(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		return uB.getAllUsers();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
		return uB.updateUser(user);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUser(User user) {
		uB.createUser(user);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUser(@PathParam("id") long id) {
		return uB.removeUser(id);
	}
	
	

}
