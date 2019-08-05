package io.altar.projetoFichaColaborador.business;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.EntityRepository;

public class UserBusiness {

	@Inject
	private EntityRepository<User> eR;

	private LoginBusiness lB;

	public void createUser(User user) {
		eR.create(user);
	}

	public Response updateUser(User user) {
		User userTry = lB.getCurrentUser();
		if (userTry.getRole() == "owner") {

			if (user.getId() == userTry.getId()) {
				if (user.getUsername() != userTry.getUsername() || user.getRole() != userTry.getRole()) {
					return Response.status(Response.Status.FORBIDDEN).entity("Nao pode alterar estes dados").build();
				} else {
					eR.update(user);
					return Response.status(Response.Status.OK).entity(user).build();
				}
			}
		} else if (user.getId() != userTry.getId()) {
			return Response.status(Response.Status.FORBIDDEN).entity("Nao tem permissao para fazer essas alteracoes")
					.build();
		} else {
			if (user.getUsername() != userTry.getUsername() || user.getRole() != userTry.getRole()) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Nao tem permissao para fazer essas alteracoes").build();
			} else {
				eR.update(user);
				return Response.status(Response.Status.OK).entity(user).build();
			}
		}
		return null;
	}

	public Response getUserById(long id) {
		User user = eR.getEntityById(id);

		if (user.getId() > 0) {
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

		User user = lB.getCurrentUser();
		User userToRemove = eR.getEntityById(id);
		boolean valid = eR.countEntityExists(userToRemove);

		if (valid) {
			if (user.getRole() != "owner") {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Nao tem permissao para eliminar este utilizador").build();

			} else if (user.getId() == lB.getCurrentUser().getId()) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Nao tem permissao para eliminar este utilizador").build();
			} else {
				eR.remove(id);
				return Response.status(Response.Status.OK).entity(user).build();
			}
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity("O utilizador que esta a tentar apagar nao existe")
					.build();
		}

	}

}
