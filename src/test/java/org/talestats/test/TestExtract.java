package org.talestats.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.talestats.dao.CityDAO;
import org.talestats.dao.CouncilDAO;
import org.talestats.dao.HeroDAO;
import org.talestats.model.City;
import org.talestats.model.Council;
import org.talestats.model.Hero;
import org.talestats.utils.CityExtract;
import org.talestats.utils.CouncilExtract;
import org.talestats.utils.HeroExtract;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = org.talestats.config.WebAppConfig.class)
public class TestExtract {
	
	private final static String CITYURL = "http://the-tale.org/game/map/places/1";
	private final static String CITYLOCALURL = "/Users/jurikolo/git/talestats_spring4/src/test/resources/TestExtract.html";
	private final static Integer CITYSIZE = 8; //Needs to be adjusted before execution
	private final static String CITYNAME = "Красные Дюны";
	private final static Integer COUNCILCOUNT = 6; //Needs to be adjusted before execution
	private final static Integer COUNCILID = 1818; //Жирослав
	private final static Integer COUNCILQUEUECOUNTER = 2;
	private final static String COUNCILNAME = "Жирослав";
	private final static String COUNCILRACE = "человек";
	private final static String COUNCILJOB = "священник";
	private final static String COUNCILSKILL = "гений";
	private final static Integer COUNCILALLIES = 10;
	private final static Integer COUNCILENEMIES = 10;
	private final static Integer COUNCILINFLUENCE = 26;
	private final static Integer HEROCOUNT = 20;
	private final static Integer HEROID = 10745;
	private final static Integer HEROQUEUECOUNTER = 0;
	
	@Autowired
	private CityExtract cityExtract;
	@Autowired
	private CouncilExtract councilExtract;
	@Autowired
	private HeroExtract heroExtract;
	
	private final Document doc = getDoc();
	private final String str = getStr();
	
	private Document getDoc() {
		Document doc = null;
		try {
			File input = new File(CITYLOCALURL);
			doc = Jsoup.parse(input, "UTF-8", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	private String getStr() {
		return doc.toString();
	}
	
	@Test
	public void cityGetSizeTest() {
		assertEquals(Integer.valueOf(CITYSIZE), Integer.valueOf(cityExtract.getSize(str)));
	}
	
	@Test
	public void cityGetNameTest() {
		assertEquals(CITYNAME, cityExtract.getName(doc));
	}
	
	@Test
	public void councilGetCountTest() {
		assertEquals(Integer.valueOf(COUNCILCOUNT), Integer.valueOf(councilExtract.getCount(doc)));
	}
	
	@Test
	public void councilGetIdTest() {
		assertEquals(Integer.valueOf(COUNCILID), Integer.valueOf(councilExtract.getId(doc, Integer.valueOf(COUNCILQUEUECOUNTER))));
	}
	
	@Test
	public void councilGetNameTest() {
		assertEquals(COUNCILNAME.trim(), councilExtract.getName(doc, COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetRaceTest() {
		assertEquals(COUNCILRACE.trim(), councilExtract.getRace(doc, COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetJobTest() {
		assertEquals(COUNCILJOB.trim(), councilExtract.getJob(doc, COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetSkillTest() {
		assertEquals(COUNCILSKILL.trim(), councilExtract.getSkill(doc, COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetAlliesTest() {
		assertEquals(Integer.valueOf(COUNCILALLIES), Integer.valueOf(councilExtract.getAllies(doc, COUNCILQUEUECOUNTER)));
	}
	
	@Test
	public void councilGetEnemiesTest() {
		assertEquals(Integer.valueOf(COUNCILENEMIES), Integer.valueOf(councilExtract.getEnemies(doc, COUNCILQUEUECOUNTER)));
	}
	
	@Test
	public void councilGetInfluenceTest() {
		assertEquals(Integer.valueOf(COUNCILINFLUENCE), Integer.valueOf(councilExtract.getInfluence(doc, COUNCILQUEUECOUNTER)));
	}
	
	@Test
	public void heroGetCountTest() {
		assertEquals(Integer.valueOf(HEROCOUNT), Integer.valueOf(heroExtract.getCount(doc, COUNCILQUEUECOUNTER)));
	}
	
	@Test
	public void heroGetIdTest() {
		assertEquals(Integer.valueOf(HEROID), Integer.valueOf(heroExtract.getId(doc, COUNCILQUEUECOUNTER, HEROQUEUECOUNTER)));
	}
	
	
}