package org.talestats.scheduled;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.dao.CityDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.dao.VoteDAO;
import org.talestats.model.City;
import org.talestats.model.Hero;
import org.talestats.model.Vote;
import org.talestats.utils.JSoup;
import org.talestats.utils.VoteExtract;

@Component
public class VoteProcess {

	static final Logger logger = LoggerFactory.getLogger(VoteProcess.class);
	@Autowired
	private HeroDAO heroDao;
	@Autowired
	private CityDAO cityDao;
	@Autowired
	private VoteDAO voteDao;
	@Autowired
	private VoteExtract voteExtract;

	public void process() {
		logger.debug("Vote processing started!!!");

		Vote vote = new Vote();
		City city = new City();
		List<Hero> heroes = heroDao.getHeroes();
		String cityName = "";
		for (Hero hero : heroes)
		{
			Document doc = JSoup.getDoc("http://the-tale.org/accounts/" + hero.getId());
			Element voteElement = doc.select("div.row-fluid").first();
			voteElement = voteElement.select("div.span6").last();
			Integer cnt = voteExtract.getCount(voteElement);
			for (int i = 1; i <= cnt; i++) {
				cityName = voteExtract.getCityName(voteElement, i);
				city = cityDao.getCityByName(cityName);
				vote.setCityId(city.getId());
				vote.setHeroId(hero.getId());
				voteDao.addVote(vote);
			}
			
		}
	}
}