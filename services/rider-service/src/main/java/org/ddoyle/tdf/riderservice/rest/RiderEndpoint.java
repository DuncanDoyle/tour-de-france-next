package org.ddoyle.tdf.riderservice.rest;

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

import org.ddoyle.tdf.riderservice.model.Rider;
import org.ddoyle.tdf.riderservice.repository.RiderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/")
public class RiderEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(RiderEndpoint.class);
	
	@Inject
	private RiderRepository riderRepository;
	
	@GET
	@Path("/riders")
	public Response getRiders() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting all riders.");
		}
		Collection<Rider> rider = riderRepository.getAll();
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
		Rider rider = riderRepository.get(riderId);
		Response response = null;
		if (rider != null) {
			response = Response.ok(rider).build();
		} else {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;	
	}
	
	@PUT
	@Path("/rider/{rider-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRider(@PathParam("rider-id") int riderId, Rider rider) {
		System.out.println("Saving rider with id: " + riderId);
		//Validate Rider and rider-id
		if (riderId != rider.getNumber()) {
			throw new IllegalArgumentException("Rider number does not match rider-id in URL.");
		}
		riderRepository.save(rider);
		return Response.ok().build();
	}
	
}