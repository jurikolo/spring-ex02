package org.talestats.scheduled;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.talestats.config.Constants;
import org.talestats.scheduled.CityProcess;
import org.talestats.scheduled.CouncilProcess;
import org.talestats.utils.CouncilExtract;
import org.talestats.utils.HeroExtract;
import org.talestats.dao.CouncilDAO;
import org.talestats.dao.GuildDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.dao.SchedulerDAO;
import org.talestats.dao.VoteDAO;

public class Scheduler {

	@Autowired
	private CityProcess cityProcess;
	@Autowired
	private CouncilProcess councilProcess;
	@Autowired
	private HeroProcess heroProcess;
	@Autowired
	private VoteProcess voteProcess;
	@Autowired
	private HeroDAO heroDao;
	@Autowired
	private GuildDAO guildDao;
	@Autowired
	private VoteDAO voteDao;
	@Autowired
	private CouncilDAO councilDao;
	@Autowired
	private SchedulerDAO schedulerDao;
	@Autowired
	private HeroExtract heroExtract;

	static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	// for tests
	//@Scheduled(initialDelay = Constants.START_DELAY, fixedDelay = Constants.REPEAT_DELAY)

	// for prod
	@Scheduled(cron = "0 0 2 * * *")
	
	public void run() throws Exception {
		logger.info("Scheduled run started");
		schedulerDao.addScheduler();
		Document doc;
		CouncilExtract councilExtract = new CouncilExtract();
		logger.info("Removing votes, heroes, guilds and councils from DB...");
		// Delete all voting to gather statistics about active accounts only
		voteDao.deleteAllVotes();
		// Delete all heroes to gather statistics about active accounts only
		heroDao.deleteAllHeroes();
		// Delete all guilds to gather statistics about existing guilds only
		guildDao.deleteAllGuilds();
		// Delete all councils to gather statistics about existing councils only
		councilDao.deleteAllCouncils();
		logger.info("DB is cleaned up");

		// Get all heroes
		heroExtract.setHeroes();

		// Gather cities stats
		for (int cityId = 1; cityId <= Constants.CITY_COUNT; cityId++) {
			logger.info("City progress: " + cityId + "/" + Constants.CITY_COUNT);
			String url = "http://the-tale.org/game/map/places/" + cityId;
			try {
				doc = Jsoup.parse(Jsoup.connect(url).timeout(Constants.TIMEOUT).get().toString(), "UTF-8");
				cityProcess.process(cityId, doc);
				for (int councilCnt = 0; councilCnt < councilExtract.getCount(doc); councilCnt++) {
					if (councilCnt != 0)
						councilProcess.process(councilCnt, doc, cityId);
					heroProcess.process(councilCnt, doc, cityId);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		voteProcess.process();

		schedulerDao.deleteScheduler();
		logger.info("Scheduled run completed");
	}
}