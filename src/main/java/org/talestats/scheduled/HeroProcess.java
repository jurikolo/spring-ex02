package org.talestats.scheduled;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.dao.CityDAO;
import org.talestats.dao.CouncilDAO;
import org.talestats.dao.GuildDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.utils.CouncilExtract;
import org.talestats.utils.GuildExtract;
import org.talestats.utils.HeroExtract;
import org.talestats.model.City;
import org.talestats.model.Guild;
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
	private GuildExtract guildExtract;
	@Autowired
	CityDAO cityDao;
	@Autowired
	GuildDAO guildDao;
	@Autowired
	CouncilDAO councilDao;

	public void process(int councilCnt, Document doc, int cityId) {
		logger.debug("Hero processing started!!!");

		int heroCnt = heroExtract.getCount(doc, councilCnt);
		boolean isAlly = false;
		for (int cnt = 0; cnt < heroCnt; cnt++) {
			int heroId = heroExtract.getId(doc, councilCnt, cnt);
			String heroName = heroExtract.getName(doc, councilCnt, cnt);
			String heroKeeper = heroExtract.getKeeper(doc, councilCnt, cnt);
			int heroAlly;
			int heroEnemy;
			City city = cityDao.getCity(cityId);
			
			//Might need to add guild first
			Guild guild = new Guild();
			int guildId = guildExtract.getId(doc, councilCnt, cnt);;
			String guildName = guildExtract.getName(doc, councilCnt, cnt);
			guild.setId(guildId);
			guild.setName(guildName);
			guildDao.addOrUpdateGuild(guild);

			Hero hero = new Hero();
			hero.setId(heroId);
			hero.setName(heroName);
			hero.setKeeper(heroKeeper);
			hero.setCity(city);
			hero.setGuild(guild);

			if (councilCnt != 0) {
				isAlly = heroExtract.isAlly(doc, councilCnt, cnt);
				if (isAlly) {
					heroAlly = councilExtract.getId(doc, councilCnt);
					hero.setAlly(councilDao.getCouncil(heroAlly));
				} else {
					heroEnemy = councilExtract.getId(doc, councilCnt);
					hero.setEnemy(councilDao.getCouncil(heroEnemy));
				}
			}

			heroDao.addOrUpdateHero(hero);
		}
	}
}