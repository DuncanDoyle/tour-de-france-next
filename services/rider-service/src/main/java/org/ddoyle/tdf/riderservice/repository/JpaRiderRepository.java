package org.ddoyle.tdf.riderservice.repository;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ddoyle.tdf.riderservice.model.Rider;

@Stateless
public class JpaRiderRepository implements RiderRepository {

	@PersistenceContext(unitName="RiderServicePersistenceUnit")
	private EntityManager em;

	@Override
	public Rider get(int id) {
		return em.find(Rider.class, id);
	}

	@Override
	public Rider save(Rider rider) {
		return em.merge(rider);
	}

	@Override
	public Collection<Rider> getAll() {
		Query query = em.createQuery("SELECT r FROM Rider r");
	    return (Collection<Rider>) query.getResultList();
	}
	
	
}
