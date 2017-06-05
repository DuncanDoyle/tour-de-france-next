package org.ddoyle.tdf.teamservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.config.datasources.xa_data_source.XADatasourceProperties;
import org.wildfly.swarm.datasources.DatasourcesFraction;

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
		
		//Configure the Swarm fractions.
		configureTeamDataSource(swarm);
		
		// Start the Swarm container and add the deployment.
		swarm.start();

		//Just deploy the default application.	
		swarm.deploy();

	}
	
	private static Swarm configureTeamDataSource(Swarm swarm) {
		swarm.fraction(new DatasourcesFraction()
			.xaDataSource("PlayerServiceDS", (ds) -> {
			ds.jndiName("java:jboss/datasources/TeamServiceDS");
			ds.driverName("postgresql");
			ds.userName("postgres");
			ds.password("postgres");
			
			List<XADatasourceProperties> xaDatasourceProperties = new ArrayList<>();
			xaDatasourceProperties.add(new XADatasourceProperties("ServerName").value("localhost"));
			xaDatasourceProperties.add(new XADatasourceProperties("PortNumber").value("5432"));
			xaDatasourceProperties.add(new XADatasourceProperties("DatabaseName").value("tdf-team-service"));
			ds.xaDatasourceProperties(xaDatasourceProperties);
			
		}));
		
		return swarm;
	}

}
