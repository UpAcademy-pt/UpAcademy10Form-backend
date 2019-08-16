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
				"SELECT COUNT(u) FROM User u WHERE u.username =:userName AND u.password=:passWord)", Long.class);
		query.setParameter("userName", userCredentials.getUsername());
		query.setParameter("passWord", lB.hashPassword(userCredentials.getPassword()));
		return query.getSingleResult().longValue() > 0;
	}

	public User getUserFromCredentials(Credentials userCredentials) {
		userCredentials.setPassword(lB.hashPassword(userCredentials.getPassword()));
		TypedQuery<User> query = em.createNamedQuery(getUserLoginQuery(), User.class);
		query.setParameter("username", userCredentials.getUsername());
		query.setParameter("password", userCredentials.getPassword());
		return query.getSingleResult();
	}
	
	public void initDb() {
		String hashedPassword = lB.hashPassword("superadmin");
		Query query = em.createNativeQuery("INSERT INTO User (created, modified, username, password, role) VALUES ('1565618095165', '1565618095165', 'superadmin', :hashedPassword, 'owner')");
		query.setParameter("hashedPassword", hashedPassword);
		query.executeUpdate();
	}
}
