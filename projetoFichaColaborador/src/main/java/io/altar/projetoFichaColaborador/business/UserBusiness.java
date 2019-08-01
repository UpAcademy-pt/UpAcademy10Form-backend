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

	public static User currentUser;

	public void createUser(User user) {
		eR.create(user);
	}

	public Response updateUser(User user) {
		if (user.getRole() == "owner") {
			if (user.getId() == currentUser.getId()) {
				if (user.getUsername() != currentUser.getUsername() || user.getRole() != currentUser.getRole()) {
					return Response.status(Response.Status.FORBIDDEN).entity("Nao pode alterar estes dados").build();
				} else {
					eR.update(user);
					return Response.status(Response.Status.OK).entity(user).build();
				}
			}
		} else if (user.getId() != currentUser.getId()) {
			return Response.status(Response.Status.FORBIDDEN).entity("Nao tem permissao para fazer essas alteracoes")
					.build();
		} else {
			if (user.getUsername() != currentUser.getUsername() || user.getRole() != currentUser.getRole()) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Nao tem permissao para fazer essas alteracoes").build();
			} else {
				eR.update(user);
				return Response.status(Response.Status.OK).entity(user).build();
			}
		}
		return null;

	}

	public Response getUserLogin(Credentials userCredentials) {
		User logedUserTry = uR.getUserFromCredentials(userCredentials);

		if (logedUserTry.getId() != null) {
			currentUser = logedUserTry;
			return Response.accepted().entity(logedUserTry).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Username e/ou Password incorrectos").build();
		}
	}

	public Response getUserById(long id) {
		User user = eR.getEntityById(id);

		if (user.getId() != null) {
			return Response.accepted().entity(user).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).entity("Esse utilizador nao existe").build();
		}
	}

	public Response getAllUsers() {
		List<User> tempAllUsers = eR.getAll();

		if (tempAllUsers != null) {
			return Response.accepted().entity(tempAllUsers).build();
		} else {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	public Response removeUser(long id) {
		User user = eR.getEntityById(id);

		if (user.getRole() != "owner") {
			return Response.status(Response.Status.FORBIDDEN).entity("Nao tem permissao para eliminar este utilizador")
					.build();

		} else if (user.getId() == currentUser.getId()) {
			return Response.status(Response.Status.FORBIDDEN).entity("Nao tem permissao para eliminar este utilizador")
					.build();
		} else {
			eR.remove(id);
			return Response.status(Response.Status.OK).entity(user).build();
		}
	}

}
