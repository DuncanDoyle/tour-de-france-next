package org.ddoyle.tdf.teamservice.repository;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ddoyle.tdf.teamservice.model.Team;

@Stateless
public class JpaTeamRepository implements TeamRepository {

	@PersistenceContext(unitName="TeamServicePersistenceUnit")
	private EntityManager em;
	
	@Override
	public Team get(int id) {
		return em.find(Team.class, id);
	}

	@Override
	public Team save(Team team) {
		return em.merge(team);
	}

	@Override
	public Collection<Team> getAll() {
		Query query = em.createQuery("SELECT t FROM Team t");
	    return (Collection<Team>) query.getResultList();
	}

}
