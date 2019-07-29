package io.altar.projetoFichaColaborador.repositories;

import javax.faces.bean.RequestScoped;

import io.altar.projetoFichaColaborador.models.User;


@RequestScoped
public class UserRepository extends EntityRepository<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
