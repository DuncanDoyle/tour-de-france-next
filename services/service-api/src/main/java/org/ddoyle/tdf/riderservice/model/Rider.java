package org.ddoyle.tdf.riderservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rider {
	
	@Id
	private int number;
	
	private String name;
	
	private String surName;
	
	private String team;
	
	public Rider() {
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
