package org.talestats.test;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.talestats.dao.CityDAO;
import org.talestats.dao.CouncilDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.model.City;
import org.talestats.model.Council;
import org.talestats.model.Hero;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = org.talestats.config.WebAppConfig.class)
public class TestApp {
	
	private final static Integer CITYID = Integer.valueOf(999);
	private final static Integer COUNCILID = Integer.valueOf(998);
	private final static Integer HEROID = Integer.valueOf(997);

	@Autowired
	private CityDAO cityDao;
	@Autowired
	private CouncilDAO councilDao;
	@Autowired
	private HeroDAO heroDao;

	@Test
	public void createCityTest() {
		City city = new City();
		city.setId(CITYID);
		city.setName("testName");
		city.setSize(10);
		cityDao.addCity(city);
		City city2 = new City();
		assertNotEquals(city, city2);
		city2 = cityDao.getCity(CITYID);
		assertEquals(city, city2);
		cityDao.deleteCity(CITYID);
	}
	
	@Test
	public void createCouncilTest() {
		Council council = new Council();
		City city = new City();
		city.setId(COUNCILID);
		city.setName("testCity");
		city.setSize(10);
		cityDao.addCity(city);
		
		council.setId(COUNCILID);
		council.setName("testName");
		council.setRace("testRace");
		council.setJob("testJob");
		council.setSkill("testSkill");
		council.setAllies(42);
		council.setEnemies(24);
		council.setInfluence(100);
		council.setCity(city);
		councilDao.addCouncil(council);
		Council council2 = new Council();
		assertNotEquals(council, council2);
		council2 = councilDao.getCouncil(COUNCILID);
		assertEquals(council, council2);
		councilDao.deleteCouncil(COUNCILID);
		cityDao.deleteCity(COUNCILID);
	}
	
	@Test
	public void createHeroTest() {
		Hero hero = new Hero();
		City city = new City();
		city.setId(HEROID);
		city.setName("testCity");
		city.setSize(10);
		cityDao.addCity(city);
		
		hero.setId(HEROID);
		hero.setName("testName");
		hero.setKeeper("testKeeper");
		hero.setGuildId(0);
		hero.setCity(city);
		heroDao.addHero(hero);
		Hero hero2 = new Hero();
		assertNotEquals(hero, hero2);
		hero2 = heroDao.getHero(HEROID);
		assertEquals(hero, hero2);
		heroDao.deleteHero(HEROID);
		cityDao.deleteCity(HEROID);
	}
	
}