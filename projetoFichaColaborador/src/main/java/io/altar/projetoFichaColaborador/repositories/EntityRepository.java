package io.altar.projetoFichaColaborador.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import io.altar.projetoFichaColaborador.models.Entity_;



@Transactional
public abstract class EntityRepository<T extends Entity_> {

	@PersistenceContext(unitName = "database")
	protected EntityManager em;
	
	protected abstract Class<T> getEntityClass();
	protected abstract String getAllQuery();
	
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
	
	public List<T> getAll() {
		TypedQuery<T> query = em.createNamedQuery(getAllQuery(), getEntityClass());
		List<T> emp = query.getResultList();
		return emp;
	}

}
