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
		String councilSkill = councilExtract.getSkill(doc, councilCnt);
		int councilAllies = councilExtract.getAllies(doc, councilCnt);
		int councilEnemies = councilExtract.getEnemies(doc, councilCnt);

		Council council = new Council();
		council.setId(councilId);
		council.setName(councilName);
		council.setRace(councilRace);
		council.setJob(councilJob);
		council.setSkill(councilSkill);
		council.setAllies(councilAllies);
		council.setEnemies(councilEnemies);
		logger.debug(council.toString());
		councilDao.addOrUpdateCouncil(council);
	}
}