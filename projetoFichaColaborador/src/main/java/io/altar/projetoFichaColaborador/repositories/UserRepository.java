package io.altar.projetoFichaColaborador.repositories;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import io.altar.projetoFichaColaborador.models.Credentials;
import io.altar.projetoFichaColaborador.models.User;

@RequestScoped
public class UserRepository extends EntityRepository<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	protected String getAllQuery() {
		return User.GET_ALL_USERS;
	}

	protected String getUserLoginQuery() {
		return User.GET_USER_LOGIN;
	}
	@Override
	protected String getByIdQuery() {
		return User.GET_USER_BY_ID;
	}

	protected String countUserExistsQuery() {
		return User.COUNT_USER_EXISTS;
	}

	public boolean countUserExists(User user) {
		long id = user.getId();
		TypedQuery<User> query = em.createNamedQuery(countUserExistsQuery(), User.class);
		query.setParameter("userId", id);
		int valid = query.getFirstResult();
		if (valid==1) {
			return true;
		}else {
			return false;
		}
	}
	
	public User getUserFromCredentials(Credentials userCredentials) {
		TypedQuery<User> query = em.createNamedQuery(getUserLoginQuery(), User.class);
		query.setParameter("username", userCredentials.getUsername());
		query.setParameter("password", userCredentials.getPassword());
		return query.getSingleResult();
	}
}
