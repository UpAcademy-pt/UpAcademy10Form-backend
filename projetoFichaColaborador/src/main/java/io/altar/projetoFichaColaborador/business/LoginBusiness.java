package io.altar.projetoFichaColaborador.business;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Credentials;
import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.UserRepository;

public class LoginBusiness {

	@Inject
	private UserRepository uR;

	private User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Response getUserLogin(Credentials userCredentials) {

		boolean valid = uR.countCredentialsExistsByEntity(userCredentials);
		System.out.println(valid);
		if (valid) {
			User logedUserTry = uR.getUserFromCredentials(userCredentials);
			setCurrentUser(logedUserTry);
			return Response.accepted().entity(logedUserTry).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Username e/ou Password incorrectos").build();
		}
	}

	public String hashPassword(String TextPassword) {
		Integer hash = TextPassword.hashCode();
		String hasedPass = hash.toString();
		return hasedPass;
	}

	public void createSuperAdmin() {
		uR.initDb();
	}

}
