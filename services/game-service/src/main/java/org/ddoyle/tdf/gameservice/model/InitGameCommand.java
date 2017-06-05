package org.ddoyle.tdf.gameservice.model;

public class InitGameCommand {

	private int numberOfStages;
	
	private int numberOfTeams;
	
	private int teamTimeTrialStage;

	public InitGameCommand() {
	}
	
	public int getNumberOfStages() {
		return numberOfStages;
	}

	public void setNumberOfStages(int numberOfStages) {
		this.numberOfStages = numberOfStages;
	}

	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public int getTeamTimeTrialStage() {
		return teamTimeTrialStage;
	}

	public void setTeamTimeTrialStage(int teamTimeTrialStage) {
		this.teamTimeTrialStage = teamTimeTrialStage;
	}

	
	
	
	
}
