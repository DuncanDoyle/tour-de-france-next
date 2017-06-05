package org.ddoyle.tdf.teamservice.rest;

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

import org.ddoyle.tdf.teamservice.model.Team;
import org.ddoyle.tdf.teamservice.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/")
public class TeamEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeamEndpoint.class);
	
	@Inject
	private TeamRepository teamRepository;
		
	@GET
	@Path("/players")
	public Response getTeams() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting all players.");
		}
		Collection<Team> rider = teamRepository.getAll();
		Response response = null;
		response = Response.ok(rider).build();
		return response;	
	}
	
	@GET
	@Path("/team/{team-id}")
	public Response getRider(@PathParam("team-id") int teamId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting rider with id: " + teamId);
		}
		Team player = teamRepository.get(teamId);
		Response response = null;
		if (player != null) {
			response = Response.ok(player).build();
		} else {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;	
	}
	
	@PUT
	@Path("/team/{team-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRider(@PathParam("team-id") int teamId, Team team) {
		System.out.println("Saving rider with id: " + teamId);
		
		if (teamId != team.getId()) {
			throw new IllegalArgumentException("Rider number does not match rider-id in URL.");
		}
		teamRepository.save(team);
		return Response.ok().build();
	}
	
	
	
	
	
}
