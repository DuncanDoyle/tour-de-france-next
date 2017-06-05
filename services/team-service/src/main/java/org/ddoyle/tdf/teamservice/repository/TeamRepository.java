package org.ddoyle.tdf.teamservice.repository;

import java.util.Collection;

import org.ddoyle.tdf.teamservice.model.Team;

public interface TeamRepository {

	Team get(int id);
	
	Team save(Team team);
	
	Collection<Team> getAll();
	
}
