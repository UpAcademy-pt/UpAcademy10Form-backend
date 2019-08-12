package io.altar.projetoFichaColaborador.business;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.repositories.EntityRepository;
import io.altar.projetoFichaColaborador.repositories.UserRepository;

public class UserBusiness {

	@Inject
	private EntityRepository<User> eR;

	@Inject
	private UserRepository uR;
	@Inject
	private LoginBusiness lB;

	public void createUser(User user) {
		user.setPassword(lB.hashPassword(user.getPassword()));
		eR.create(user);
	}

	public Response updateUser(User user) {

		User userTry = lB.getCurrentUser();

//		User userTry = new User();
//		userTry.setId(2);
//		userTry.setRole("admin");
//		userTry.setUsername("admin");
//		userTry.setPassword("superadmin");
//		System.out.println(userTry.getUsername());
//		System.out.println(userTry.getPassword());
//		System.out.println(userTry.getRole());
//		System.out.println("_________------------------__________");
//		System.out.println(user.getUsername());
//		System.out.println(user.getPassword());
//		System.out.println(user.getRole());

		if (userTry.getRole() == "owner") {
			if (user.getId() == userTry.getId()) {
				if (!user.getUsername().equals(userTry.getUsername()) || !user.getRole().equals(userTry.getRole())) {
					return Response.status(Response.Status.NOT_FOUND).entity("1-Nao pode alterar estes dados").build();
				}
				user.setModified(Instant.now().toEpochMilli());
				updateExecution(user);
				return Response.ok(user, MediaType.APPLICATION_JSON).build();
			}
			user.setModified(Instant.now().toEpochMilli());
			updateExecution(user);
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		} else if (user.getId() != userTry.getId()) {
			return Response.status(Response.Status.NOT_FOUND).entity("2-Nao pode alterar estes dados").build();
		}
		if (!user.getUsername().equals(userTry.getUsername()) || !user.getRole().equals(userTry.getRole())) {
			return Response.status(Response.Status.NOT_FOUND).entity("3-Nao pode alterar estes dados").build();
		} else {
			user.setModified(Instant.now().toEpochMilli());
			updateExecution(user);
			;
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		}
	}

	private void updateExecution(User user) {
		String hashedPassword = lB.hashPassword(user.getPassword());
		user.setPassword(hashedPassword);
		user.setModified(Instant.now().toEpochMilli());
		eR.update(user);
	}

	public Response getUserById(long id) {
		boolean valid = uR.countUserExistsById(id);
		if (valid) {
			User user = eR.getEntityById(id);
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Esse utilizador nao existe").build();
		}
	}

	public Response getAllUsers() {
		List<User> tempAllUsers = eR.getAll();
		if (tempAllUsers.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity("Nao existem utilizadores criados").build();
		} else {
			return Response.ok(tempAllUsers, MediaType.APPLICATION_JSON).build();
		}
	}

	public Response removeUser(long id) {

//		User user = lB.getCurrentUser();

		User user = new User();
//		user.setId(1);
//		user.setRole("owner");
//		user.setUsername("admin");
//		user.setPassword("superadmin");

		boolean valid = uR.countUserExistsById(id);

		if (valid) {
			User userToRemove = eR.getEntityById(id);
			if (user.getRole() != "owner") {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Nao tem permissao para eliminar este utilizador").build();
			} else if (user.getId() == userToRemove.getId()) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Nao tem permissao para eliminar este utilizador").build();
			}
			eR.remove(id);
			return Response.ok(userToRemove, MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("O utilizador que esta a tentar apagar nao existe")
				.build();
	}

}
