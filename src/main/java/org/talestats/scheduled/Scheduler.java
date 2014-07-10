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

public class Scheduler {

	@Autowired
	private CityProcess cityProcess;
	@Autowired
	private CouncilProcess councilProcess;

	static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	@Scheduled(initialDelay = Constants.START_DELAY, fixedRate = Constants.REPEAT_DELAY)
	public void run() {
		logger.info("Scheduler started");
		Document doc;
		CouncilExtract councilExtract = new CouncilExtract();
		for (int cityId = 1; cityId <= Constants.CITY_COUNT; cityId++) {
			String url = "http://the-tale.org/game/map/places/" + cityId;
			try {
				doc = Jsoup.parse(Jsoup.connect(url).get().toString(), "UTF-8");
				String str = Jsoup.connect(url).get().toString();
				cityProcess.process(cityId, doc, str);
				for (int councilCnt = 1; councilCnt < councilExtract.getCount(doc); councilCnt++) {
					councilProcess.process(councilCnt, doc, str, cityId);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}