package org.talestats.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.config.Constants;
import org.talestats.dao.HeroDAO;
import org.talestats.dao.HeroHistoryDAO;
import org.talestats.model.Hero;
import org.talestats.model.HeroExtra;
import org.talestats.model.HeroHistory;
import org.talestats.scheduled.Scheduler;

//TODO this class requires huge refactoring
@Component
public class HeroExtract {

	static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	@Autowired
	private HeroDAO heroDao;
	@Autowired
	private HeroHistoryDAO heroHistoryDao;

	private Document doc;
	private int pageCount;
	private ArrayList<Integer> heroes = new ArrayList<Integer>();

	public HeroExtract() {
	}

	public HeroExtract(int page) {
		if (page <= 0) {
			return;
		}
		try {
			doc = Jsoup.connect(Constants.RatingsUrl + page).get();
			// doc = Jsoup.parse(new File("data/hero-levels.html"),"utf8");
			findPageCount();
			extractHeroes();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	private void findPageCount() {
		Element pages = doc.select("div.pagination").first();
		pageCount = Integer.parseInt(pages.child(0).children().last().text());
	}

	private void extractHeroes() {
		Element heroes = doc.select("table.table").first().child(1);
		for (Element hero : heroes.children()) {
			parseHero(hero);
		}
	}

	private void parseHero(Element hero) {
		Element idElement = hero.select("a[href^=/game/heroes/]").first();
		int heroId = Integer.parseInt(idElement.attr("href").replaceAll("/game/heroes/", ""));
		heroes.add(heroId);
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getHeroCount() {
		return heroes.size();
	}

	public ArrayList<Integer> getHeroIds() {
		return heroes;
	}

	// This method adds all existing heroes basic information to DB
	public void setHeroes() throws Exception {
		logger.info("Setting heroes...");
		int cnt = 0;
		HeroExtract heroExtrFirst = new HeroExtract(1);
		final int pageCount = heroExtrFirst.getPageCount();
		HeroExtract heroExtr = new HeroExtract(pageCount);
		// New number of heroes in the DB.
		final int newHeroes = heroExtrFirst.getHeroCount() * (pageCount - 1) + heroExtr.getHeroCount();
		int currPage = heroExtrFirst.getPageCount();
		// Run through hero pages
		for (; newHeroes > 0 && currPage > 0; heroExtr = new HeroExtract(--currPage)) {
			try {
				Thread.sleep(Constants.RATE);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			ArrayList<Integer> readHeroes = heroExtr.getHeroIds();
			for (int heroId : readHeroes) {
				try {
					Thread.sleep(Constants.RATE);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				// Increase counter
				cnt++;
				if (cnt % 1000 == 0) {
					logger.info("Heroes set so far: " + cnt);
				}

				String json = getJson(heroId);

				// Add / update hero id in DB
				Hero hero = new Hero();
				hero.setId(heroId);
				hero.setName(getNameByJson(json));
				hero.setAllyId(0);
				hero.setEnemyId(0);
				hero.setGuildId(0);
				hero.setCityId(0);
				hero.setSubscribed(isSubscriber(json));
				hero.setPoliticallyActive(IsPoliticallyActive(heroId));
				hero.setLevel(getLevel(json));
				hero.setLastVisitTimeStamp(getLastVisitTimeStamp(json));
				heroDao.addOrUpdateHero(hero);
				
				// Check if hero name is changed
				if (nameChanged(hero.getId(), hero.getName())) {
					HeroHistory heroHistory = new HeroHistory();
					heroHistory.setId(hero.getId());
					heroHistory.setName(hero.getName());
					heroHistoryDao.addHeroHistory(heroHistory);
				}
			}
		}
		logger.info("Heroes are set");
		logger.info("Total number of heroes: " + cnt);
	}

	private boolean nameChanged(int id, String name) {
		List<HeroHistory> heroHistoryList = heroHistoryDao.getHeroHistory(id);
		if (heroHistoryList.size() < 1)
			return true;
		for (HeroHistory heroHistory : heroHistoryList) {
			if (heroHistory.getName().equals(name))
				return false;
		}
		return true;
		
	}
	
	public String getJson(int heroId) throws Exception {
		final String jsonAnswer = Request.Get(Constants.heroInfoPath + heroId).execute().returnContent().asString();
		if (!jsonAnswer.matches(Constants.statusPattern)) {
			// Bad hero?
			throw new Exception("There was a problem getting hero information: '" + jsonAnswer + "'");
		}
		return jsonAnswer;
	}

	public int isSubscriber(String json) {
		if (json.replaceFirst(Constants.canRepairPattern, "$1").toLowerCase().equals("true")) {
			return 1;
		}
		return 0;
	}

	public int getLevel(String json) {
		return Integer.parseInt(json.replaceFirst(Constants.levelPattern, "$1"));
	}

	public int getLastVisitTimeStamp(String json) {
		return Integer.parseInt(json.replaceFirst(Constants.lastVisitPattern, "$1"));
	}

	public int IsPoliticallyActive(int heroId) {
		int isPoliticallyActive = 1;
		try {
			doc = Jsoup.connect(Constants.AchievementsUrl + heroId).get();
			// doc = Jsoup.parse(new File("data/hero-achievements.html"),
			// "utf8");
			isPoliticallyActive = checkPoliticalActivity();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return isPoliticallyActive;

	}

	// Extracts political activity of the person
	private int checkPoliticalActivity() {
		Element politicsHref = doc.select("a[href^=/accounts/achievements/politics?account=]").last();
		if (!politicsHref.parent().select("div.progress").text().equals("0 / 13 (0%)")) {
			return 1;
		}
		return 0;
	}

	public int getCount(Document doc, int cnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int result = 0;
		while (m.find()) {
			result++;
		}
		return result;
	}

	public int getId(Document doc, int cnt, int heroCnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int TmpCnt = 0;
		while (m.find() && heroCnt != TmpCnt) {
			TmpCnt++;
			str = str.substring(str.indexOf("/game/heroes") + 10);
		}
		str = str.substring(str.indexOf("/game/heroes") + 13);
		str = str.substring(0, str.indexOf("\""));
		return Integer.parseInt(str);
	}

	public String getNameByJson(String json) {
		String nameByJson = json.replaceFirst(Constants.heroNamePattern, "$1");
		if (nameByJson.length() > 99) {
			return "-";
		} else {
			return nameByJson;
		}
	}

	public boolean isAlly(Document doc, int cnt, int heroCnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int TmpCnt = 0;
		while (m.find() && heroCnt != TmpCnt) {
			TmpCnt++;
			str = str.substring(str.indexOf("/game/heroes") + 10);
		}
		str = str.substring(str.indexOf("/game/heroes") + 13);
		int ally = str.indexOf("</tr>");
		int enemy = str.indexOf("<td>");
		if ((enemy == -1) || (ally < enemy)) {
			return false;
		} else {
			return true;
		}
	}

	public String getNameByDoc(Document doc, int cnt, int heroCnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int TmpCnt = 0;
		while (m.find() && heroCnt != TmpCnt) {
			TmpCnt++;
			str = str.substring(str.indexOf("/game/heroes") + 10);
		}
		str = str.substring(str.indexOf("/game/heroes") + 13);
		str = str.substring(str.indexOf("\">") + 2, str.indexOf("</a>"));
		if (null == str) {
			return "-";
		}
		return str.trim();
	}

	public String getKeeper(Document doc, int cnt, int heroCnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int TmpCnt = 0;
		while (m.find() && heroCnt != TmpCnt) {
			TmpCnt++;
			str = str.substring(str.indexOf("/game/heroes") + 10);
		}
		str = str.substring(str.indexOf("/game/heroes") + 13);
		str = str.substring(str.indexOf("/accounts/") + 10);
		str = str.substring(str.indexOf("\">") + 2, str.indexOf("</a>"));
		if (null == str) {
			return "-";
		}
		return str.trim();
	}

	public int getGuildId(Document doc, int cnt, int heroCnt) {
		Elements divs = doc.select("div.accordion-group");
		Element div = divs.get(cnt);
		String str = div.toString();
		Pattern p = Pattern.compile("/game/heroes");
		Matcher m = p.matcher(str);
		int TmpCnt = 0;
		while (m.find() && heroCnt != TmpCnt) {
			TmpCnt++;
			str = str.substring(str.indexOf("/game/heroes") + 10);
		}
		str = str.substring(str.indexOf("/game/heroes") + 13);
		int clan = str.indexOf("clans");
		int noClan = str.indexOf("</td>");
		if ((clan == -1) || (noClan < clan)) {
			return 0;
		} else {
			str = str.substring(str.indexOf("clans") + 6);
			str = str.substring(0, str.indexOf("\""));
			return Integer.parseInt(str);
		}
	}
}