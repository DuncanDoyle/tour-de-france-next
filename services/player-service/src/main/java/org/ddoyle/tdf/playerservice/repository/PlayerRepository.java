package org.ddoyle.tdf.playerservice.repository;

import java.util.Collection;

import org.ddoyle.tdf.playerservice.model.Player;

public interface PlayerRepository {

	Player get(int id);
	
	Player save(Player player);
	
	Collection<Player> getAll();
	
}
