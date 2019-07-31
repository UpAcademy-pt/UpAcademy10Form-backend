package io.altar.projetoFichaColaborador.business;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.Credentials;
import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.EntityRepository;
import io.altar.projetoFichaColaborador.repositories.UserRepository;

public class UserBusiness {

	@Inject
	private EntityRepository<User> eR;
	@Inject
	private UserRepository uR;

	public Response businessGetAllUsers() {
		List<User> tempAllUsers = eR.getAll();

		if (tempAllUsers != null) {
			return Response.accepted().entity(tempAllUsers).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	public Response userLogin(Credentials userCredentials) {
		User logedUser = uR.getUserLogin(userCredentials);

		if (logedUser.getId() != null) {
			return Response.accepted().entity(logedUser).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Username e/ou Password incorrectos").build();
		}
	}

}
