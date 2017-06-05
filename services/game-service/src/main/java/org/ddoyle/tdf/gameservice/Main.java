package org.ddoyle.tdf.gameservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.swarm.Swarm;

/**
 * Main class for the Swarm-based micro-service.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {

		LOGGER.info("Bootstrapping WildFly-Swarm.");

		Swarm swarm = new Swarm();
				
		// Start the Swarm container and add the deployment.
		swarm.start();
		
		//Just deploy the default application.	
		swarm.deploy();
	}
}
