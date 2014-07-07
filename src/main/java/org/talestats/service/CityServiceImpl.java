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
	private CityDAO cityDAO;

	@Override
	public void addCity(City city) {
		cityDAO.addCity(city);
	}

	@Override
	public void updateCity(City city) {
		cityDAO.updateCity(city);
	}

	@Override
	public City getCity(int id) {
		return cityDAO.getCity(id);
	}

	@Override
	public void deleteCity(int id) {
		cityDAO.deleteCity(id);
	}

	@Override
	public List<City> getCities() {
		return cityDAO.getCities();
	}

}
