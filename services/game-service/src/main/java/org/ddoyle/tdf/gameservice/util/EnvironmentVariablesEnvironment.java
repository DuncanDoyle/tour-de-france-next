package org.ddoyle.tdf.gameservice.util;

public class EnvironmentVariablesEnvironment implements Environment {

	private static final String PLAYER_SERVICE_URL_ENV_VARIABLE_NAME = "PLAYER_SERVICE_URL";
	
	private static final String PLAYER_SERVICE_URL = System.getenv(PLAYER_SERVICE_URL_ENV_VARIABLE_NAME);
	
	
	@Override
	public String getPlayerServiceUrl() {
		return PLAYER_SERVICE_URL;
	}

}
