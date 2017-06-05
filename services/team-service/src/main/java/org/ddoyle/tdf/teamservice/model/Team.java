package org.ddoyle.tdf.teamservice.model;

import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	private int id;
	
	private int playerId;
	
	@ElementCollection
	Collection<Integer> riderIds;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public Collection<Integer> getRiderIds() {
		return riderIds;
	}

	public void setRiderIds(Collection<Integer> riderIds) {
		this.riderIds = riderIds;
	}
	
	public void addRiderId(int id) {
		riderIds.add(id);
	}
	
}
