package org.ddoyle.tdf.gameservice.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ddoyle.tdf.playerservice.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class JaxRsPlayerServiceClient implements PlayerServiceClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JaxRsPlayerServiceClient.class);

	private Client client;

	private WebTarget webTarget;

	private String playerServiceUrl = "http://localhost:8080/";

	@PostConstruct
	public void init() {
		client = ClientBuilder.newClient();
		webTarget = client.target(playerServiceUrl);
	}

	public void savePlayer(Player player) {
		Response response = webTarget.path("/player").path("{player-id}").resolveTemplate("player-id", player.getId())
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(Entity.json(player));

		// Validate response status
		if (response.getStatus() != Response.Status.OK.getStatusCode() || response.getStatus() != Response.Status.CREATED.getStatusCode()
				|| response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {

		} else {
			//TODO: We need some better error handling.
			String message = "Error saving player.";
			LOGGER.error(message);
			throw new RuntimeException(message);
		}
	}

}
