package org.ddoyle.tdf.gameservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentVariablesEnvironment implements Environment {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentVariablesEnvironment.class);
	
	private static final String PLAYER_SERVICE_URL_ENV_VARIABLE_NAME = "PLAYER_SERVICE_URL";
	
	private static final String PLAYER_SERVICE_URL = System.getenv(PLAYER_SERVICE_URL_ENV_VARIABLE_NAME);

	public EnvironmentVariablesEnvironment() {
		LOGGER.info("Loading EnvironmentVariables Environment.");
	} 
	
	@Override
	public String getPlayerServiceUrl() {
		return PLAYER_SERVICE_URL;
	}

}
