package io.altar.projetoFichaColaborador.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import io.altar.projetoFichaColaborador.business.UserBusiness;
import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.UserRepository;


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
	
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public List<User> allUsers() {
//		return ub.getAll();
//	}
	
}
