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

	public static User currentUser;

	public Response getUserLogin(Credentials userCredentials) {
		if (uR.countCredentialsExistsByEntity(userCredentials)) {
			User logedUser = uR.getUserFromCredentials(userCredentials);
			currentUser = logedUser;
			userCredentials.setId(logedUser.getId());
			userCredentials.setRole(logedUser.getRole());
			userCredentials.setPassword("");
			return Response.ok(userCredentials, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).entity("Username e/ou Password incorrectos").build();
		}
	}
}
