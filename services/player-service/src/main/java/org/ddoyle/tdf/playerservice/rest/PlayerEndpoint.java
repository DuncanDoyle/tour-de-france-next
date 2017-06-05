package org.ddoyle.tdf.playerservice.rest;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ddoyle.tdf.playerservice.model.Player;
import org.ddoyle.tdf.playerservice.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/")
public class PlayerEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerEndpoint.class);
	
	@Inject
	private PlayerRepository playerRepository;
		
	@GET
	@Path("/players")
	public Response getPlayers() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting all players.");
		}
		Collection<Player> rider = playerRepository.getAll();
		Response response = null;
		response = Response.ok(rider).build();
		return response;	
	}
	
	@GET
	@Path("/rider/{rider-id}")
	public Response getRider(@PathParam("rider-id") int riderId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting rider with id: " + riderId);
		}
		Player player = playerRepository.get(riderId);
		Response response = null;
		if (player != null) {
			response = Response.ok(player).build();
		} else {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;	
	}
	
	@PUT
	@Path("/player/{player-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRider(@PathParam("player-id") int playerId, Player player) {
		System.out.println("Saving rider with id: " + playerId);
		//Validate Player and player-id
		if (playerId != player.getId()) {
			throw new IllegalArgumentException("Rider number does not match rider-id in URL.");
		}
		playerRepository.save(player);
		return Response.ok().build();
	}
	
	
	
	
	
}
