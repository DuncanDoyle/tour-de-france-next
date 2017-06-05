package org.ddoyle.tdf.playerservice.repository;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ddoyle.tdf.playerservice.model.Player;

@Stateless
public class JpaPlayerRepository implements PlayerRepository {

	@PersistenceContext(unitName="PlayerServicePersistenceUnit")
	private EntityManager em;
	
	@Override
	public Player get(int id) {
		return em.find(Player.class, id);
	}

	@Override
	public Player save(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Player> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
