package org.talestats.test;

import static org.junit.Assert.*;

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
	private final static Integer CITYSIZE = 8; //Needs to be adjusted before execution
	private final static String CITYNAME = "Красные Дюны";
	private final static Integer COUNCILCOUNT = 6; //Needs to be adjusted before execution
	private final static Integer COUNCILID = 1818; //Жирослав
	private final static Integer COUNCILQUEUECOUNTER = 2; //Needs to be adjusted before execution
	private final static String COUNCILNAME = "Жирослав";
	private final static String COUNCILRACE = "человек";
	private final static String COUNCILJOB = "священник";
	private final static String COUNCILSKILL = "гений";
	private final static Integer COUNCILALLIES = 10;
	
	@Autowired
	private CityExtract cityExtract;
	@Autowired
	private CouncilExtract councilExtract;
	@Autowired
	private HeroExtract heroExtract;
	
	private Document getDoc() {
		Document doc = null;
		try {
			doc = Jsoup.parse(Jsoup.connect(CITYURL).get().toString(), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	private String getStr(Document doc) {
		try {
			return Jsoup.connect(CITYURL).get().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@Test
	public void cityGetSizeTest() {
		assertEquals(Integer.valueOf(CITYSIZE), Integer.valueOf(cityExtract.getSize(getStr(getDoc()))));
	}
	
	@Test
	public void cityGetNameTest() {
		assertEquals(CITYNAME, cityExtract.getName(getDoc()));
	}
	
	@Test
	public void councilGetCountTest() {
		assertEquals(Integer.valueOf(COUNCILCOUNT), Integer.valueOf(councilExtract.getCount(getDoc())));
	}
	
	@Test
	public void councilGetIdTest() {
		assertEquals(Integer.valueOf(COUNCILID), Integer.valueOf(councilExtract.getId(getDoc(), Integer.valueOf(COUNCILQUEUECOUNTER))));
	}
	
	@Test
	public void councilGetNameTest() {
		assertEquals(COUNCILNAME.trim(), councilExtract.getName(getDoc(), COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetRaceTest() {
		assertEquals(COUNCILRACE.trim(), councilExtract.getRace(getDoc(), COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetJobTest() {
		assertEquals(COUNCILJOB.trim(), councilExtract.getJob(getDoc(), COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetSkillTest() {
		assertEquals(COUNCILSKILL.trim(), councilExtract.getSkill(getDoc(), COUNCILQUEUECOUNTER).trim());
	}
	
	@Test
	public void councilGetAlliesTest() {
		assertEquals(Integer.valueOf(COUNCILALLIES), Integer.valueOf(councilExtract.getAllies(getDoc(), COUNCILQUEUECOUNTER)));
	}
	
}