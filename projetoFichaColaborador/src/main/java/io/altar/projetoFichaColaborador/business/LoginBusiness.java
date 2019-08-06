package io.altar.projetoFichaColaborador.business;

import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Credentials;
import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.UserRepository;

public class LoginBusiness {

	private UserRepository uR;

	private User currentUser;
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Response getUserLogin(Credentials userCredentials) {
		User logedUserTry = uR.getUserFromCredentials(userCredentials);
		boolean valid = uR.countEntityExists(logedUserTry);
		if (valid) {
			setCurrentUser(logedUserTry);
			return Response.accepted().entity(logedUserTry).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Username e/ou Password incorrectos").build();
		}
	}

}
