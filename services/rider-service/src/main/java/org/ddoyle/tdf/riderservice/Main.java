package org.ddoyle.tdf.riderservice;

import java.util.ArrayList;
import java.util.List;

import org.ddoyle.tdf.riderservice.rest.RiderEndpoint;
import org.ddoyle.tdf.riderservice.rest.RiderServiceApplication;
import org.jboss.shrinkwrap.api.ShrinkWrap;
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

	public static void main(String[] args) throws Exception {

		System.out.println("Bootstrapping WildFly-Swarm.");

		Swarm swarm = new Swarm();
		
		// Create the JAXRS application.
		// Can't specify archive name. If we, Swarm will not deploy our app.
		JAXRSArchive riderService = ShrinkWrap.create(JAXRSArchive.class);
		// JAXRSArchive riderService = ShrinkWrap.create(JAXRSArchive.class, "rider-service");

		// Add required classes to deployment.
		riderService.addClass(RiderServiceApplication.class);
		riderService.addClass(RiderEndpoint.class);

		//Configure the Swarm fractions.
		configureRiderDataSource(swarm);
		
		// Start the Swarm container and add the deployment.
		swarm.start();
		swarm.deploy(riderService);

	}

	private static Swarm configureRiderDataSource(Swarm swarm) {
		swarm.fraction(new DatasourcesFraction().jdbcDriver("postgresql", (d) -> {
			d.xaDatasourceClass("org.postgresql.xa.PGXADataSource");
			d.driverModuleName("org.postgresql");
		}).xaDataSource("RiderServiceDS", (ds) -> {
			ds.jndiName("java:jboss/datasources/RiderServiceDS");
			ds.driverName("posgresql");
			ds.userName("postgres");
			ds.password("postgres");
			
			List<XADatasourceProperties> xaDatasourceProperties = new ArrayList<>();
			xaDatasourceProperties.add(new XADatasourceProperties("ServerName").value("localhost"));
			xaDatasourceProperties.add(new XADatasourceProperties("PortNumber").value("5432"));
			xaDatasourceProperties.add(new XADatasourceProperties("DatabaseName").value("tdf-rider-service"));
			ds.xaDatasourceProperties(xaDatasourceProperties);
			
		}));
		/*		
				
				dataSource("RiderServiceDS", (ds) -> {
			ds.driverName("postgresql");
			ds.jndiName("java:jboss/datasources/RiderServiceDS");
			ds.serv
			ds.userName("postgres");
			ds.password("postgres");
		}));
		*/
		return swarm;
	}

}
