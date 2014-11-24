package org.talestats.scheduled;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.config.Constants;
import org.talestats.dao.CityDAO;
import org.talestats.dao.GuildDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.utils.CouncilExtract;
import org.talestats.utils.GuildExtract;
import org.talestats.utils.HeroExtract;
import org.talestats.utils.HeroExtractNew;
import org.talestats.model.Guild;
import org.talestats.model.Hero;

@Component
public class HeroProcess {

	static final Logger logger = LoggerFactory.getLogger(HeroProcess.class);
	private static int delay = 1000; // Delay between consequent requests to the-tale.org
	@Autowired
	private HeroExtract heroExtract;
	@Autowired
	private HeroDAO heroDao;
	@Autowired
	private CouncilExtract councilExtract;
	@Autowired
	private GuildExtract guildExtract;
	@Autowired
	CityDAO cityDao;
	@Autowired
	GuildDAO guildDao;

	// New
	@Autowired
	private HeroExtractNew heroExtractNew;

	public void process(int councilCnt, Document doc, int cityId) {
		logger.debug("Hero processing started!!!");

		// New
		HeroExtractNew heroExtrFirst = new HeroExtractNew(1);
		final int pageCount = heroExtrFirst.GetPageCount();
		HeroExtractNew heroExtr = new HeroExtractNew(pageCount);
		// New number of heros in the DB.
		final int newHeros = heroExtrFirst.GetHeroCount() * (pageCount - 1)
				+ heroExtr.GetHeroCount();
		int currPage = heroExtrFirst.GetPageCount();
		// Do while we are not going to find all the new heros.
		/*for (; newHeros > 0 && currPage > 0; heroExtr = new HeroExtractNew(
				--currPage), Thread.sleep(delay)) {
			ArrayList<Integer> readHeros = heroExtr.GetHeroIds();
			for (int heroId : readHeros) {
				// Add / update hero id in DB
				Hero hero = new Hero();
				//db.UpdateHeroInfo(new HeroInfo(id));
			}

		}*/

		// Old

		int heroCnt = heroExtract.getCount(doc, councilCnt);
		boolean isAlly = false;
		for (int cnt = 0; cnt < heroCnt; cnt++) {
			int heroId = heroExtract.getId(doc, councilCnt, cnt);

			Hero hero = new Hero();
			if (null != heroDao.getHero(heroId)) {
				hero = heroDao.getHero(heroId);
			} else {
				String heroName = heroExtract.getName(doc, councilCnt, cnt);
				String heroKeeper = heroExtract.getKeeper(doc, councilCnt, cnt);

				// Might need to add guild first
				Guild guild = new Guild();
				int guildId = guildExtract.getId(doc, councilCnt, cnt);
				String guildName = guildExtract.getName(doc, councilCnt, cnt);
				int guildSize = guildDao.getGuildSize(guildId);
				guild.setId(guildId);
				guild.setName(guildName);
				guild.setSize(guildSize);
				guildDao.addOrUpdateGuild(guild);

				hero.setId(heroId);
				hero.setName(heroName);
				hero.setKeeper(heroKeeper);
				hero.setCityId(cityId);
				hero.setGuildId(guildId);
			}

			if (councilCnt != 0) {
				isAlly = heroExtract.isAlly(doc, councilCnt, cnt);
				if (isAlly) {
					hero.setAllyId(councilExtract.getId(doc, councilCnt));
				} else {
					hero.setEnemyId(councilExtract.getId(doc, councilCnt));
				}
			}
			// TODO
			// Added temporary check for last city id (Targard)
			if (null == heroDao.getHero(heroId)
					&& (cityId > Constants.CIVIL_CITY_COUNT)
					&& (cityId < Constants.CITY_COUNT)) {
				logger.debug("Hero has no subscription");
			} else {
				heroDao.addOrUpdateHero(hero);
			}
		}
	}
}