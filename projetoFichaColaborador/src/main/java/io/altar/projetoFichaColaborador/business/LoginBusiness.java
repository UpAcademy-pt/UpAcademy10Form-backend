package io.altar.projetoFichaColaborador.business;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
		if (valid) {
			User logedUser = uR.getUserFromCredentials(userCredentials);
			setCurrentUser(logedUser);
			userCredentials.setId(logedUser.getId());
			userCredentials.setRole(logedUser.getRole());
			userCredentials.setPassword("");
			return Response.ok(userCredentials, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).entity("Username e/ou Password incorrectos").build();
		}
	}

	public String hashPassword(String TextPassword) {
		Integer hash = TextPassword.hashCode();
		String hashedPass = hash.toString();
		return hashedPass;
	}

	public void createSuperAdmin() {
		uR.initDb();
	}

}
