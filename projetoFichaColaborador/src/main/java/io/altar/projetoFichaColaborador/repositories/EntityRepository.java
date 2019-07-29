package io.altar.projetoFichaColaborador.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.altar.projetoFichaColaborador.models.Entity_;



@Transactional
public abstract class EntityRepository<T extends Entity_> {

	@PersistenceContext(unitName = "database")
	protected EntityManager em;
	
	public T addEntity(T entity) {
		return em.merge(entity);
	}

	public T getEntity(long id) {
		return em.find(getEntityClass(), id);
	}

	public void removeEntity(long id) {
		em.remove(id);
		
	}

	public void editEntity(T entity) {
		em.merge(entity);
	}

	protected abstract Class<T> getEntityClass();

}
