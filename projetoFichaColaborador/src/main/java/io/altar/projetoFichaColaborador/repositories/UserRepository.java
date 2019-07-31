package io.altar.projetoFichaColaborador.repositories;

import javax.enterprise.context.RequestScoped;

import io.altar.projetoFichaColaborador.models.User;

@RequestScoped
public class UserRepository extends EntityRepository<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	protected String getAllQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}
