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
import org.talestats.dao.GuildDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.model.City;
import org.talestats.model.Council;
import org.talestats.model.Guild;
import org.talestats.model.Hero;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = org.talestats.config.WebAppConfig.class)
public class TestDao {
	
	private final static Integer CITYID = Integer.valueOf(999);
	private final static String CITYNAME = "testName";
	private final static Integer CITYSIZE = Integer.valueOf(10);	
	private final static Integer COUNCILID = Integer.valueOf(989);
	private final static String COUNCILNAME = "testName";
	private final static String COUNCILRACE = "testRace";
	private final static String COUNCILJOB = "testJob";
	private final static String COUNCILSKILL = "testSkill";
	private final static Integer COUNCILALLIES = Integer.valueOf(42);
	private final static Integer COUNCILENEMIES = Integer.valueOf(24);
	private final static Integer COUNCILINFLUENCE = Integer.valueOf(100);
	private final static Integer HEROID = Integer.valueOf(979);
	private final static String HERONAME = "testName";
	private final static String HEROKEEPER = "testKeeper";
	private final static Integer HEROALLY = 400;
	private final static Integer HEROENEMY = 500;
	private final static Integer GUILDID = 30;
	private final static String GUILDNAME = "testName";


	@Autowired
	private CityDAO cityDao;
	@Autowired
	private CouncilDAO councilDao;
	@Autowired
	private HeroDAO heroDao;
	@Autowired
	private GuildDAO guildDao;

	@Test
	public void createCityTest() {
		City city = new City();
		city.setId(CITYID);
		city.setName(CITYNAME);
		city.setSize(10);
		cityDao.addCity(city);
		
		assertEquals(CITYID, Integer.valueOf(cityDao.getCity(CITYID).getId()));
		assertEquals(CITYNAME, cityDao.getCity(CITYID).getName());
		assertEquals(CITYSIZE, Integer.valueOf(cityDao.getCity(CITYID).getSize()));
		
		City city2 = new City();
		assertNotEquals(city, city2);
		city2 = cityDao.getCity(CITYID);
		assertEquals(city, city2);
		
		cityDao.deleteCity(CITYID);
	}
	
	@Test
	public void updateCityTest() {
		City city = new City();
		city.setId(CITYID);
		city.setName(CITYNAME);
		city.setSize(CITYSIZE);
		cityDao.addCity(city);
		
		city.setName("testName2");
		city.setSize(11);
		cityDao.updateCity(city);
		
		assertEquals("testName2", cityDao.getCity(CITYID).getName());
		assertEquals(Integer.valueOf(11), Integer.valueOf(cityDao.getCity(CITYID).getSize()));
		
		cityDao.deleteCity(CITYID);
	}
	
	@Test
	public void createCouncilTest() {
		Council council = new Council();
		City city = new City();
		city.setId(COUNCILID);
		city.setName(CITYNAME);
		city.setSize(CITYSIZE);
		cityDao.addCity(city);
		
		council.setId(COUNCILID);
		council.setName(COUNCILNAME);
		council.setRace(COUNCILRACE);
		council.setJob(COUNCILJOB);
		council.setSkill(COUNCILSKILL);
		council.setAllies(COUNCILALLIES);
		council.setEnemies(COUNCILENEMIES);
		council.setInfluence(COUNCILINFLUENCE);
		council.setCity(city);
		councilDao.addCouncil(council);
		
		assertEquals(Integer.valueOf(COUNCILID), Integer.valueOf(councilDao.getCouncil(COUNCILID).getId()));
		assertEquals(COUNCILNAME, councilDao.getCouncil(COUNCILID).getName());
		assertEquals(COUNCILRACE, councilDao.getCouncil(COUNCILID).getRace());
		assertEquals(COUNCILJOB, councilDao.getCouncil(COUNCILID).getJob());
		assertEquals(COUNCILSKILL, councilDao.getCouncil(COUNCILID).getSkill());
		assertEquals(Integer.valueOf(COUNCILALLIES), Integer.valueOf(councilDao.getCouncil(COUNCILID).getAllies()));
		assertEquals(Integer.valueOf(COUNCILENEMIES), Integer.valueOf(councilDao.getCouncil(COUNCILID).getEnemies()));
		assertEquals(Integer.valueOf(COUNCILINFLUENCE), Integer.valueOf(councilDao.getCouncil(COUNCILID).getInfluence()));
		
		Council council2 = new Council();
		assertNotEquals(council, council2);
		council2 = councilDao.getCouncil(COUNCILID);
		assertEquals(council, council2);
		
		councilDao.deleteCouncil(COUNCILID);
		cityDao.deleteCity(COUNCILID);
	}
	
	@Test
	public void updateCouncilTest() {
		Council council = new Council();
		City city = new City();
		city.setId(COUNCILID);
		city.setName(CITYNAME);
		city.setSize(CITYSIZE);
		cityDao.addCity(city);
		
		council.setId(COUNCILID);
		council.setName(COUNCILNAME);
		council.setRace(COUNCILRACE);
		council.setJob(COUNCILJOB);
		council.setSkill(COUNCILSKILL);
		council.setAllies(COUNCILALLIES);
		council.setEnemies(COUNCILENEMIES);
		council.setInfluence(COUNCILINFLUENCE);
		council.setCity(city);
		councilDao.addCouncil(council);
		
		council.setName("testName2");
		council.setRace("testRace2");
		council.setJob("testJob2");
		council.setSkill("testSkill2");
		council.setAllies(43);
		council.setEnemies(34);
		council.setInfluence(99);	
		councilDao.updateCouncil(council);
		
		assertEquals("testName2", councilDao.getCouncil(COUNCILID).getName());
		assertEquals("testRace2", councilDao.getCouncil(COUNCILID).getRace());
		assertEquals("testJob2", councilDao.getCouncil(COUNCILID).getJob());
		assertEquals("testSkill2", councilDao.getCouncil(COUNCILID).getSkill());
		assertEquals(Integer.valueOf(43), Integer.valueOf(councilDao.getCouncil(COUNCILID).getAllies()));
		assertEquals(Integer.valueOf(34), Integer.valueOf(councilDao.getCouncil(COUNCILID).getEnemies()));
		assertEquals(Integer.valueOf(99), Integer.valueOf(councilDao.getCouncil(COUNCILID).getInfluence()));
		
		councilDao.deleteCouncil(COUNCILID);
		cityDao.deleteCity(COUNCILID);
	}
	
	@Test
	public void createHeroTest() {
		Hero hero = new Hero();
		City city = new City();
		city.setId(HEROID);
		city.setName(CITYNAME);
		city.setSize(CITYSIZE);
		cityDao.addCity(city);
		
		Guild guild = new Guild();
		guild.setId(GUILDID);
		guild.setName(GUILDNAME);
		guildDao.addGuild(guild);
		
		hero.setId(HEROID);
		hero.setName(HERONAME);
		hero.setKeeper(HEROKEEPER);
		hero.setAlly(HEROALLY);
		hero.setEnemy(HEROENEMY);
		hero.setCity(city);
		hero.setGuild(guild);
		heroDao.addHero(hero);
		
		assertEquals(HEROID, Integer.valueOf(heroDao.getHero(HEROID).getId()));
		assertEquals(HERONAME, heroDao.getHero(HEROID).getName());
		assertEquals(HEROKEEPER, heroDao.getHero(HEROID).getKeeper());
		assertEquals(Integer.valueOf(HEROALLY), Integer.valueOf(heroDao.getHero(HEROID).getAlly()));
		assertEquals(Integer.valueOf(HEROENEMY), Integer.valueOf(heroDao.getHero(HEROID).getEnemy()));
		
		Hero hero2 = new Hero();
		assertNotEquals(hero, hero2);
		hero2 = heroDao.getHero(HEROID);
		assertEquals(hero, hero2);
		
		heroDao.deleteHero(HEROID);
		guildDao.deleteGuild(GUILDID);
		cityDao.deleteCity(HEROID);
	}
	
	@Test
	public void updateHeroTest() {
		Hero hero = new Hero();
		City city = new City();
		city.setId(HEROID);
		city.setName(CITYNAME);
		city.setSize(CITYSIZE);
		cityDao.addCity(city);
		
		Guild guild = new Guild();
		guild.setId(GUILDID);
		guild.setName(GUILDNAME);
		guildDao.addGuild(guild);
		
		hero.setId(HEROID);
		hero.setName(HERONAME);
		hero.setKeeper(HEROKEEPER);
		hero.setAlly(HEROALLY);
		hero.setEnemy(HEROENEMY);
		hero.setCity(city);
		hero.setGuild(guild);
		heroDao.addHero(hero);
		
		hero.setName("testName2");
		hero.setKeeper("testKeeper2");
		hero.setAlly(401);
		hero.setEnemy(501);
		heroDao.updateHero(hero);
		
		assertEquals("testName2", heroDao.getHero(HEROID).getName());
		assertEquals("testKeeper2", heroDao.getHero(HEROID).getKeeper());
		assertEquals(Integer.valueOf(401), Integer.valueOf(heroDao.getHero(HEROID).getAlly()));
		assertEquals(Integer.valueOf(501), Integer.valueOf(heroDao.getHero(HEROID).getEnemy()));
		
		heroDao.deleteHero(HEROID);
		guildDao.deleteGuild(GUILDID);
		cityDao.deleteCity(HEROID);
	}
	
	@Test
	public void createGuildTest() {
		Guild guild = new Guild();
		guild.setId(GUILDID);
		guild.setName(GUILDNAME);
		guildDao.addGuild(guild);
		
		assertEquals(GUILDID, Integer.valueOf(guildDao.getGuild(GUILDID).getId()));
		assertEquals(GUILDNAME, guildDao.getGuild(GUILDID).getName());
		
		Guild guild2 = new Guild();
		assertNotEquals(guild, guild2);
		guild2 = guildDao.getGuild(GUILDID);
		assertEquals(guild, guild2);
		
		guildDao.deleteGuild(GUILDID);
	}
	
	@Test
	public void updateGuildTest() {
		Guild guild = new Guild();
		guild.setId(GUILDID);
		guild.setName(GUILDNAME);
		guildDao.addGuild(guild);
		
		guild.setName("testName2");
		guildDao.updateGuild(guild);
		
		assertEquals("testName2", guildDao.getGuild(GUILDID).getName());
		
		guildDao.deleteGuild(GUILDID);
	}	
}