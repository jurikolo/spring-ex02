package org.talestats.config;

public class Constants {

	//Amount of Pandora cities
	public final static Integer CITY_COUNT = Integer.valueOf(44);
	//Amount of Pandora civil cities
	public final static Integer CIVIL_CITY_COUNT = Integer.valueOf(27);
	//City extract delay after application is started
	public final static long START_DELAY = 20000L;
	//City extract repeat delay
	public final static long REPEAT_DELAY = 18000000L;
	//Scheduler fixed repeat rate
	public final static long REPEAT_RATE = 86400000L;
	//Set timeout to 10 seconds for a document download
	public final static Integer TIMEOUT = 10000;
	//Set rate for hero extract
	public final static long RATE = 1000L;
	//API URL
	public static final String heroInfoPath = "http://the-tale.org/game/api/info?api_version=1.0&api_client=MonksSubscriberChecker-1.0&account=";
	//Status pattern
	public static final String statusPattern = ".*\"status\": \"ok\".*";
	//Repair pattern
	public static final String canRepairPattern = ".*\"can_repair_building\":\\s*(\\w+).*";
	//Hero ratings URL
	public static final String RatingsUrl = "http://the-tale.org/game/ratings/level?page=";
	//Last visit
	public static final String lastVisitPattern = ".*\"last_visit\":\\s*(\\d+)(\\D\\d+)?.*";
	//Level pattern
	public static final String levelPattern = ".*\"level\":\\s*(\\d+).*";
	//Keeper achievements URL
	public static String AchievementsUrl = "http://the-tale.org/accounts/achievements/?account=";
	//Hero name pattern
	public static final String heroNamePattern = "";
}