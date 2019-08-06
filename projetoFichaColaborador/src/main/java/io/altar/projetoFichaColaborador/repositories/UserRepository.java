package io.altar.projetoFichaColaborador.repositories;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import io.altar.projetoFichaColaborador.business.LoginBusiness;
import io.altar.projetoFichaColaborador.models.Credentials;
import io.altar.projetoFichaColaborador.models.User;

@RequestScoped
public class UserRepository extends EntityRepository<User> {
@Inject
	private LoginBusiness lB;

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

	public User getUserFromCredentials(Credentials userCredentials) {
		TypedQuery<User> query = em.createNamedQuery(getUserLoginQuery(), User.class);
		
		query.setParameter("username", userCredentials.getUsername());
		
		String hashedPassword = lB.hashPassword(userCredentials.getPassword());
		
		query.setParameter("password", hashedPassword);
		
		return query.getSingleResult();
	}

	public void initDb() {
		String hashedPassword = lB.hashPassword("superadmin");
		Query query = em.createNativeQuery("INSERT INTO User (username, password, role) VALUES ('superadmin', :hashedPassword, 'owner')");
		query.setParameter("hashedPassword", hashedPassword);
		query.executeUpdate();
	}
	
	public boolean countUserExists(User user) {
		long id = user.getId();
		Query query = em.createQuery("SELECT count(u)  FROM User u WHERE u.id =:userId");
		query.setParameter("userId", id);
		int valid = query.getFirstResult();
		if (valid==1) {
			return true;
		}else {
			return false;
		}
	}
}

