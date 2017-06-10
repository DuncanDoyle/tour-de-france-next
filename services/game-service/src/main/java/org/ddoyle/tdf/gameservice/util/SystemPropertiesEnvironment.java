package org.ddoyle.tdf.gameservice.util;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemPropertiesEnvironment implements Environment {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemPropertiesEnvironment.class);
	
	private static final String PLAYER_SERVICE_URL_SYSTEM_PROPERT_NAME = "player.service.url";
	
	private static final String PLAYER_SERVICE_URL = System.getProperty(PLAYER_SERVICE_URL_SYSTEM_PROPERT_NAME);
	
	public SystemPropertiesEnvironment() {
		LOGGER.info("Loading SystemProperties Environment.");
	}
	
	
	@Override
	public String getPlayerServiceUrl() {
		return PLAYER_SERVICE_URL;
	}
	
	
}
