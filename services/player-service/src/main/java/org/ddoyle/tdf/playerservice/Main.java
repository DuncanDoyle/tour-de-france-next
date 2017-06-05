package org.ddoyle.tdf.playerservice;

import java.util.ArrayList;
import java.util.List;

import org.ddoyle.tdf.playerservice.model.Player;
import org.ddoyle.tdf.playerservice.rest.PlayerEndpoint;
import org.ddoyle.tdf.playerservice.rest.PlayerServiceApplication;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.config.datasources.xa_data_source.XADatasourceProperties;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

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
		
		// Create the JAXRS application.
		//JAXRSArchive riderService = buildRiderServiceArchive();
		
		//Configure the Swarm fractions.
		configurePlayerDataSource(swarm);
		
		// Start the Swarm container and add the deployment.
		swarm.start();
		//swarm.deploy(riderService);
		//Just deploy the default application.	
		swarm.deploy();

	}
	
	private static JAXRSArchive buildPlayerServiceArchive() {
		JAXRSArchive playerService = ShrinkWrap.create(JAXRSArchive.class);
		// JAXRSArchive riderService = ShrinkWrap.create(JAXRSArchive.class, "rider-service");

		// Add required classes to deployment.
		playerService.addClass(PlayerServiceApplication.class);
		playerService.addClass(PlayerEndpoint.class);
		playerService.addClass(Player.class);
		
		playerService.addAsWebInfResource(
			      new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
		return playerService;
		
	}

	private static Swarm configurePlayerDataSource(Swarm swarm) {
		swarm.fraction(new DatasourcesFraction()
			.xaDataSource("PlayerServiceDS", (ds) -> {
			ds.jndiName("java:jboss/datasources/PlayerServiceDS");
			ds.driverName("postgresql");
			ds.userName("postgres");
			ds.password("postgres");
			
			List<XADatasourceProperties> xaDatasourceProperties = new ArrayList<>();
			xaDatasourceProperties.add(new XADatasourceProperties("ServerName").value("localhost"));
			xaDatasourceProperties.add(new XADatasourceProperties("PortNumber").value("5432"));
			xaDatasourceProperties.add(new XADatasourceProperties("DatabaseName").value("tdf-player-service"));
			ds.xaDatasourceProperties(xaDatasourceProperties);
			
		}));
		
		return swarm;
	}

}
