package org.talestats.scheduled;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.dao.CouncilDAO;
import org.talestats.utils.CouncilExtract;
import org.talestats.model.Council;

@Component
public class CouncilProcess {

	static final Logger logger = LoggerFactory.getLogger(CouncilProcess.class);
	@Autowired
	private CouncilExtract councilExtract;
	@Autowired
	private CouncilDAO councilDao;

	public void process(int councilCnt, Document doc, String str, int cityId) {
		logger.debug("Council processing started!!!");

		int councilId = councilExtract.getId(doc, councilCnt);
		String councilName = councilExtract.getName(doc, councilCnt);
		String councilRace = councilExtract.getRace(doc, councilCnt);
		String councilJob = councilExtract.getJob(doc, councilCnt);

		Council council = new Council();
		council.setId(councilId);
		logger.debug("Council id: " + council.getId());
		council.setCityId(cityId);
		logger.debug("City id: " + council.getCityId());
		council.setName(councilName);
		logger.debug("Council name: " + council.getName());
		council.setRace(councilRace);
		logger.debug("Council race: " + council.getRace());
		council.setJob(councilJob);
		logger.debug("Council job: " + council.getJob());
		council.setSkill("stub");
		logger.debug("Council skill: " + council.getSkill());
		council.setAllies(0);
		logger.debug("Council allies: " + council.getAllies());
		council.setEnemies(0);
		logger.debug("Council enemies: " + council.getEnemies());
		councilDao.addOrUpdateCouncil(council);
	}
}