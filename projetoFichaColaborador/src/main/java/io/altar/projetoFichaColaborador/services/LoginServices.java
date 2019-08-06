package io.altar.projetoFichaColaborador.services;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.business.LoginBusiness;
import io.altar.projetoFichaColaborador.models.Credentials;

@Path("login")
public class LoginServices {

	@Inject
	private LoginBusiness lB;

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response getUserLogin(Credentials userCredential) {
		return lB.getUserLogin(userCredential);
	}
	
	
	@GET
	@Path("initDb")
	public void createSuperAdminEndPoint() {
		lB.createSuperAdmin();
	}
}
