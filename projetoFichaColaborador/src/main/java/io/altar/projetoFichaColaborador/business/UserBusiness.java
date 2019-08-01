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
	
	public static Credentials currentUser;

	public Response businessGetAllUsers() {
		List<User> tempAllUsers = eR.getAll();

		if (tempAllUsers != null) {
			return Response.accepted().entity(tempAllUsers).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	public Response userLogin(Credentials userCredentials) {
		User logedUserTry = uR.getUserFromCredentials(userCredentials);

		if (logedUserTry.getId() != null) {
			currentUser = userCredentials;
			return Response.accepted().entity(logedUserTry).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Username e/ou Password incorrectos").build();
		}
	}

	public void createUser(User user) {
		eR.create(user);
	}

	public Response updateUser(User user) {
		
		if (user.getId() != null) {
			
			
		}
		eR.update(user);
		return Response.status(Response.Status.NOT_FOUND).entity("Este nao existe").build();
		
	}

}
