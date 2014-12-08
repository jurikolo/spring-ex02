package org.talestats.scheduled;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.dao.CityDAO;
import org.talestats.dao.GuildDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.utils.CouncilExtract;
import org.talestats.utils.GuildExtract;
import org.talestats.utils.HeroExtract;
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

	public void process(int councilCnt, Document doc, int cityId) {

		int heroCnt = heroExtract.getCount(doc, councilCnt);
		boolean isAlly = false;
		for (int cnt = 0; cnt < heroCnt; cnt++) {
			int heroId = heroExtract.getId(doc, councilCnt, cnt);
			Hero hero = heroDao.getHero(heroId);
			if ((null != hero) && (null != hero.getKeeper())) {
				logger.debug("Existing hero");
			} else {
				String heroName = heroExtract.getNameByDoc(doc, councilCnt, cnt);
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
			heroDao.addOrUpdateHero(hero);
		}
	}
}