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
	private final static Integer CITYSIZE = 7;
	private final static String CITYNAME = "Красные Дюны";
	
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
		assertEquals(Integer.valueOf(7), cityExtract.getSize(getStr(getDoc())));
	}
	
	@Test
	public void cityGetNameTest() {
		assertEquals(CITYNAME, cityExtract.getName(getDoc()));
	}
	
}