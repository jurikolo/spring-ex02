package org.talestats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talestats.dao.CityDAO;
import org.talestats.model.City;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDAO cityDao;

	@Override
	public void addCity(City city) {
		cityDao.addCity(city);
	}

	@Override
	public void updateCity(City city) {
		cityDao.updateCity(city);
	}

	@Override
	public City getCity(int id) {
		return cityDao.getCity(id);
	}

	@Override
	public void deleteCity(int id) {
		cityDao.deleteCity(id);
	}

	@Override
	public List<City> getCities() {
		return cityDao.getCities();
	}

}
