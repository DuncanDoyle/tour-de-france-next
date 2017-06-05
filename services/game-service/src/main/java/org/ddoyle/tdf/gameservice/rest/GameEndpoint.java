package org.ddoyle.tdf.gameservice.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.ddoyle.tdf.gameservice.model.InitGameCommand;
import org.ddoyle.tdf.gameservice.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/game")
public class GameEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameEndpoint.class);
	
	@Inject
	private GameService gameService;
	
	@POST
	@Path("/init")
	public Response initGame(InitGameCommand initGameCommand) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Initializing game.");
		}
		
		gameService.initGame(initGameCommand);
		
		return Response.ok().build();
	}
	
}
