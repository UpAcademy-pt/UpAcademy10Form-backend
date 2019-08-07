package io.altar.projetoFichaColaborador.repositories;

import java.util.List;

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
		
		userCredentials.setPassword(lB.hashPassword(userCredentials.getPassword()));
		System.out.println(userCredentials.getPassword());
		TypedQuery<User> query = em.createNamedQuery(getUserLoginQuery(), User.class);
		
		query.setParameter("username", userCredentials.getUsername());
		
		query.setParameter("password", userCredentials.getPassword());
		
		return query.getSingleResult();
	}

	public void initDb() {
		String hashedPassword = lB.hashPassword("superadmin");
		Query query = em.createNativeQuery("INSERT INTO User (username, password, role) VALUES ('superadmin', :hashedPassword, 'owner')");
		query.setParameter("hashedPassword", hashedPassword);
		query.executeUpdate();
	}
	
	public boolean countCredentialsExistsByEntity(Credentials userCredentials) {
		Query query = em.createQuery("SELECT u FROM User u" + " WHERE EXISTS (SELECT u FROM User u WHERE u.username =:userName AND u.password=:passWord)");
		query.setParameter("userName", userCredentials.getUsername());
		System.out.println(userCredentials.getUsername());
		System.out.println(lB.hashPassword(userCredentials.getPassword()));
		query.setParameter("passWord", lB.hashPassword(userCredentials.getPassword()));
		List<?> valid = query.getResultList();
		
		
		System.out.println(valid);
		if (valid.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean countUserExistsByEntity(User user) {
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
	
	public boolean countUserExistsById(long id) {;
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

