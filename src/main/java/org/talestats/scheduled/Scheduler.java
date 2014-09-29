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
	private SchedulerDAO schedulerDao;

	static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	@Scheduled(initialDelay = Constants.START_DELAY, fixedRate = Constants.REPEAT_DELAY)
	public void run() {
		logger.info("Scheduled run started");
		schedulerDao.addScheduler();
		Document doc;
		CouncilExtract councilExtract = new CouncilExtract();
		//Delete all voting to gather statistics about active accounts only
		voteDao.deleteAllVotes();
		//Delete all heroes to gather statistics about active accounts only
		heroDao.deleteAllHeroes();
		//Delete all guilds to gather statistics about existing guilds only
		guildDao.deleteAllGuilds();
		
		//Gather civil cities stats
		for (int cityId = 1; cityId <= Constants.CIVIL_CITY_COUNT; cityId++) {
			logger.info("Scheduler progress: " + cityId + "/" + Constants.CITY_COUNT);
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
		
		//Gather frontier cities stats
		for (int cityId = Constants.CIVIL_CITY_COUNT; cityId + 1 <= Constants.CITY_COUNT; cityId++) {
			logger.info("Scheduler progress: " + cityId + "/" + Constants.CITY_COUNT);
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