package io.altar.projetoFichaColaborador.repositories;

import javax.persistence.TypedQuery;

import io.altar.projetoFichaColaborador.models.Credentials;
import io.altar.projetoFichaColaborador.models.User;
import io.altar.projetoFichaColaborador.utils.LoginHelper;

public class UserRepository extends EntityRepository<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	protected String getByIdQuery() {
		return User.GET_USER_BY_ID;
	}

	@Override
	protected String getAllQuery() {
		return User.GET_ALL_USERS;
	}

	protected String getUserLoginQuery() {
		return User.GET_USER_LOGIN;
	}

	@Override
	protected String checkEntityExistsByIdQuery() {
		return User.CHECK_USER_EXISTS_BY_ID;
	}

	public boolean checkUserExistsByEntity(User user) {
		return checkEntityExistsById(user.getId());
	}

	public boolean countCredentialsExistsByEntity(Credentials userCredentials) {
		TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(u) FROM User u WHERE u.username =:userName AND u.password=:passWord", Long.class);
		query.setParameter("userName", userCredentials.getUsername());
		query.setParameter("passWord", LoginHelper.hashPassword(userCredentials.getPassword()));
		return query.getSingleResult().longValue() > 0;
	}

	public User getUserFromCredentials(Credentials userCredentials) {
		userCredentials.setPassword(LoginHelper.hashPassword(userCredentials.getPassword()));
		TypedQuery<User> query = em.createNamedQuery(getUserLoginQuery(), User.class);
		query.setParameter("username", userCredentials.getUsername());
		query.setParameter("password", userCredentials.getPassword());
		return query.getSingleResult();
	}
}
