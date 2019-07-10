package com.axonactive.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericService<E,B> {
	
	@PersistenceContext(name="demo")
	EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	public void save(E entity) {
		if (entity != null) {
			em.persist(entity);
		}
	}
	
	public void update(E entity) {
		em.merge(entity);
	}
	
	public void remove(E entity) {
		em.remove(entity);
	}
}
