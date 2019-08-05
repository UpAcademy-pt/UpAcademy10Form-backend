package io.altar.projetoFichaColaborador.repositories;

import java.time.Instant;
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

	protected abstract String getByIdQuery();

	public T create(T entity) {
		entity.setCreated(Instant.now());
		return em.merge(entity);
	}

	public void update(T entity) {
		em.merge(entity);
	}

	public void remove(long id) {
		T entity = em.find(getEntityClass(), id);
		em.remove(entity);
	}

	public T findEntity(long id) {
		return em.find(getEntityClass(), id);
	}

	public T getEntityById(long id) {
		TypedQuery<T> query = em.createNamedQuery(getByIdQuery(), getEntityClass());
		query.setParameter("userId", id);
		return query.getSingleResult();
	}

	public List<T> getAll() {
		TypedQuery<T> query = em.createNamedQuery(getAllQuery(), getEntityClass());
		return query.getResultList();
	}

}
