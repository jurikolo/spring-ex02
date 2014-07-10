package org.talestats.scheduled;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.dao.HeroDAO;
import org.talestats.utils.HeroExtract;
import org.talestats.model.Hero;

@Component
public class HeroProcess {

	static final Logger logger = LoggerFactory.getLogger(HeroProcess.class);
	@Autowired
	private HeroExtract heroExtract;
	@Autowired
	private HeroDAO heroDao;

	public void process(int councilCnt, Document doc, int cityId) {
		logger.debug("Hero processing started!!!");

		int heroCnt = heroExtract.getCount(doc, councilCnt);
		boolean isAlly = false;
		for (int cnt = 0; cnt < heroCnt; cnt++) {
			int heroId = heroExtract.getId(doc, councilCnt, cnt);
			String heroName = heroExtract.getName(doc, councilCnt, cnt);
			int guildId = heroExtract.getGuildId(doc, councilCnt, cnt);

			if (councilCnt != 0)
				isAlly = heroExtract.isAlly(doc, councilCnt, cnt);
			
			Hero hero = new Hero();
			hero.setId(heroId);
			hero.setName(heroName);
			hero.setGuildId(guildId);
			hero.setAlly(0);
			hero.setEnemy(0);
			heroDao.addOrUpdateHero(hero);
		}
	}
}