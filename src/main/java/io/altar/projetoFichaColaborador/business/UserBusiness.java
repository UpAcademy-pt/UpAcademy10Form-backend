package io.altar.projetoFichaColaborador.business;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.UserRepository;
import io.altar.projetoFichaColaborador.utils.LoginHelper;

public class UserBusiness {

	@Inject
	private UserRepository uR;

	@Transactional
	public void createUser(User user) {
		user.setPassword(LoginHelper.hashPassword(user.getPassword()));
		uR.create(user);
	}

	public UserBusiness() {
	}

	@Transactional
	public Response updateUser(User user) {
		if (uR.checkUserExistsByEntity(user)) {
			user.setModified(Instant.now().toEpochMilli());
			updateExecutionSetHashPassSetModifiedInstant(user);
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Não pode alterar estes dados").build();
	}

	private void updateExecutionSetHashPassSetModifiedInstant(User user) {
		user.setPassword(LoginHelper.hashPassword(user.getPassword()));
		uR.update(user);
	}

	@Transactional
	public Response removeUser(long id) {
		
		if (uR.checkEntityExistsById(id)) {
			
			if (uR.getEntityById(id).getRole() == "owner") {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("Não tem permissões para eliminar este utilizador").build();
			}
			uR.remove(id);
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("O utilizador que esta a tentar apagar não existe")
				.build();
	}

	public Response getUserById(long id) {
		if (uR.checkEntityExistsById(id)) {
			return Response.ok(uR.getEntityById(id), MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Este utilizador não existe").build();
		}
	}

	public Response getAllUsers() {
		List<User> tempAllUsers = uR.getAll();
		if (tempAllUsers.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Nao existem utilizadores criados").build();
		} else {
			return Response.ok(tempAllUsers, MediaType.APPLICATION_JSON).build();
		}
	}

}
