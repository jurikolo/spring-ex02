package org.talestats.scheduled;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.dao.CityDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.utils.CouncilExtract;
import org.talestats.utils.HeroExtract;
import org.talestats.model.City;
import org.talestats.model.Hero;

@Component
public class HeroProcess {

	static final Logger logger = LoggerFactory.getLogger(HeroProcess.class);
	@Autowired
	private HeroExtract heroExtract;
	@Autowired
	private HeroDAO heroDao;
	@Autowired
	private CouncilExtract councilExtract;
	@Autowired
	CityDAO cityDao;

	public void process(int councilCnt, Document doc, int cityId) {
		logger.debug("Hero processing started!!!");

		int heroCnt = heroExtract.getCount(doc, councilCnt);
		boolean isAlly = false;
		for (int cnt = 0; cnt < heroCnt; cnt++) {
			int heroId = heroExtract.getId(doc, councilCnt, cnt);
			String heroName = heroExtract.getName(doc, councilCnt, cnt);
			String heroKeeper = heroExtract.getKeeper(doc, councilCnt, cnt);
			int heroGuildId = heroExtract.getGuildId(doc, councilCnt, cnt);
			int heroAlly;
			int heroEnemy;
			City city = cityDao.getCity(cityId);

			Hero hero = new Hero();
			hero.setId(heroId);
			hero.setName(heroName);
			hero.setKeeper(heroKeeper);
			hero.setGuildId(heroGuildId);
			hero.setCity(city);

			if (councilCnt != 0) {
				isAlly = heroExtract.isAlly(doc, councilCnt, cnt);
				if (isAlly) {
					heroAlly = councilExtract.getId(doc, councilCnt);
					hero.setAlly(heroAlly);
				} else {
					heroEnemy = councilExtract.getId(doc, councilCnt);
					hero.setEnemy(heroEnemy);
				}
			}

			heroDao.addOrUpdateHero(hero);
		}
	}
}