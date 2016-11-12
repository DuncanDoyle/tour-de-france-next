package org.ddoyle.tdf.riderservice.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/riders")
public class RiderEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(RiderEndpoint.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRiders() {
		LOGGER.debug("Getting all riders.");
		return Response.ok("Hello from WildFly Swarm!").build();
	}
	
	
	@GET
	@Path("/{rider-id}")
	public Response getRider(@PathParam("rider-id") String riderId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting rider with id: " + riderId);
		}
		
		
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{rider-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRider(@PathParam("rider-id") String riderId) {
		System.out.println("Saving rider with id: " + riderId);
		return Response.ok().build();
	}
}