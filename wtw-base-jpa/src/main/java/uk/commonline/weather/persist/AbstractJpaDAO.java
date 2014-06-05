package uk.commonline.weather.persist;

import static org.springframework.util.Assert.notNull;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uk.commonline.data.access.Dao;
import uk.commonline.data.model.EI;

public abstract class AbstractJpaDAO<T extends EI<T>> implements Dao<T>{
	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	public void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}
	
	@Override
	public T create(final T entity) {
		notNull(entity, clazz.getName() + " can't be null");
		entityManager.persist(entity);
		return entity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return entityManager
			.createQuery("from " + clazz.getName())
			.getResultList();
	}

	//@Override
	//@SuppressWarnings("unchecked")
	//public List<Contact> findByEmail(String email) {
	//	notNull(email, "email can't be null");
		//return entityManager
	//		.createNamedQuery("findContactsByEmail")
	//		.setParameter("email", "%" + email + "%")
	//		.getResultList();
	//}

	@Override
	public T get(Serializable id) {
		notNull(id, "id can't be null");
		
		// This returns null when the object doesn't exist
		return entityManager.find(clazz, id);
	}

	@Override
	public T load(Serializable id) {
		notNull(id, "id can't be null");
		
		T entity = get(id);
		if (entity == null) {
			throw new RuntimeException("No such " + clazz.getName() + ": " + id);
		}
		return entity;
	}

	@Override
	public T update(final T entity) {
		notNull(entity, clazz.getName() + " can't be null");
		System.out.println("!Update entity:"+entity);
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(final T entity) {
		notNull(entity, clazz.getName() + " can't be null");
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		notNull(id, "id can't be null");
		entityManager
			.createQuery("delete from " + clazz.getName() + " where id = :id")
			.setParameter("id", id)
			.executeUpdate();
	}

	@Override
	public void deleteAll() {
		entityManager
			.createQuery("delete from " + clazz.getName())
			.executeUpdate();
	}

	@Override
	public long count() {
		return (Long) entityManager
			.createQuery("select count(*) from " + clazz.getName())
			.getSingleResult();
	}

	@Override
	public boolean exists(Serializable id) {
		notNull(id, "id can't be null");
		return (get(id) != null);
	}
	
	protected final EntityManager getEntityManager() {
		return entityManager;
	}
}
