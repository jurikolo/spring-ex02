package org.talestats.scheduled;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talestats.dao.CityDAO;
import org.talestats.utils.CityExtract;
import org.talestats.model.City;

@Component
public class CityProcess {

	static final Logger logger = LoggerFactory.getLogger(CityProcess.class);
	@Autowired
	private CityExtract cityExtract;
	@Autowired
	private CityDAO cityDao;

	public void process(int cityId, Document doc, String str) {
		logger.info("City processing started!!!");

		int citySize = cityExtract.getSize(str);
		String cityName = cityExtract.getName(doc);

		City city = new City();
		city.setName(cityName);
		logger.info("City name: " + cityName);
		city.setSize(citySize);
		logger.info("City size: " + citySize);
		city.setId(cityId);
		logger.info("City id: " + cityId);
		cityDao.addOrUpdateCity(city);
	}
}