package org.ddoyle.tdf.gameservice.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.ddoyle.tdf.gameservice.client.PlayerServiceClient;
import org.ddoyle.tdf.gameservice.model.InitGameCommand;

@ApplicationScoped
public class GameService {
	
	@Inject
	private PlayerServiceClient playerService;
	
	public void initGame(InitGameCommand initGameCommand) {
		//Create players.
		createPlayers(initGameCommand.getNumberOfTeams());
		
		//Create Stages
		createStages(initGameCommand.getNumberOfStages(), initGameCommand.getTeamTimeTrialStage());
	}
	
	private void createPlayers(int numberOfPlayers) {
		
	}
	
	
	private void createStages(int numberOfStages, int teamTimeTrialStageNumber) {
		
		
	}
	
	
	
	
	
}
