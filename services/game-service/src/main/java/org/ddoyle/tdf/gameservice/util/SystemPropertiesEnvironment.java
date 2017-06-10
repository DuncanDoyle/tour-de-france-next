package org.ddoyle.tdf.gameservice.util;

public class SystemPropertiesEnvironment implements Environment {

	private static final String PLAYER_SERVICE_URL_SYSTEM_PROPERT_NAME = "player.service.url";
	
	private static final String PLAYER_SERVICE_URL = System.getProperty(PLAYER_SERVICE_URL_SYSTEM_PROPERT_NAME);

	@Override
	public String getPlayerServiceUrl() {
		return PLAYER_SERVICE_URL;
	}
	
	
	
	
}
