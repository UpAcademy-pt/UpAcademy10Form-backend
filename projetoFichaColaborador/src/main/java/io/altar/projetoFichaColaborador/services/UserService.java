package io.altar.projetoFichaColaborador.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import io.altar.projetoFichaColaborador.business.EmailBusiness;
import io.altar.projetoFichaColaborador.business.TokenGenerator;
import io.altar.projetoFichaColaborador.business.UserBusiness;
import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.UserRepository;


@Path("user")
public class UserService {
	
	@Context
	private UriInfo context;
	
	@Inject
	private UserBusiness ub;
	
	@Inject
	private EmailBusiness eb;
	
	@Inject
	private TokenGenerator tg;
	
	@GET
	@Path("isOk")
	@Produces(MediaType.TEXT_PLAIN)
	public String healthCheck() {
		return "URI " + context.getRequestUri().toString() + " is OK!";
	}
	
	@GET
	@Path("checkEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkEmail() {
		eb.sendEmail();
		return "URI " + context.getRequestUri().toString() + " is OK!";
	}
	
	@GET
	@Path("checkTokenGenerator")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkTokenGenerator() {
		return tg.generateNewToken();
	}
	
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public List<User> allUsers() {
//		return ub.getAll();
//	}
	
}
