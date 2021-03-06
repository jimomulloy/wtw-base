package uk.commonline.weather.persist;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import uk.commonline.data.access.Dao;
import uk.commonline.data.model.EI;

public abstract class AbstractJpaDAO<T extends EI> implements Dao<T> {
    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public long count() {
        return (Long) entityManager.createQuery("select count(*) from " + clazz.getName()).getSingleResult();
    }

    @Override
    @Transactional
    public T create(final T entity) {
        notNull(entity, clazz.getName() + " can't be null");
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(final T entity) {
        notNull(entity, clazz.getName() + " can't be null");
        entityManager.remove(entity);
    }

    // @Override
    // @SuppressWarnings("unchecked")
    // public List<Contact> findByEmail(String email) {
    // notNull(email, "email can't be null");
    // return entityManager
    // .createNamedQuery("findContactsByEmail")
    // .setParameter("email", "%" + email + "%")
    // .getResultList();
    // }

    @Override
    public void deleteAll() {
        entityManager.createQuery("delete from " + clazz.getName()).executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        notNull(id, "id can't be null");
        entityManager.createQuery("delete from " + clazz.getName() + " where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public boolean exists(Long id) {
        notNull(id, "id can't be null");
        return (get(id) != null);
    }

    @Override
    public T get(Long id) {
        notNull(id, "id can't be null");

        // This returns null when the object doesn't exist
        return entityManager.find(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    protected final EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T load(Long id) {
        notNull(id, "id can't be null");

        T entity = get(id);
        if (entity == null) {
            throw new RuntimeException("No such " + clazz.getName() + ": " + id);
        }
        return entity;
    }

    public void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    @Override
    @Transactional
    public T update(final T entity) {
        notNull(entity, clazz.getName() + " can't be null");
        entityManager.merge(entity);
        // entityManager.refresh(entity);
        return entity;
    }
}
