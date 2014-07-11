package org.talestats.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.City;

@Repository
public class CityDAOImpl implements CityDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCity(City city) {
		Session openSession = sessionFactory.openSession();
		openSession.save(city);
		openSession.flush();
	}

	public void updateCity(City city) {
		Session openSession = sessionFactory.openSession();
		City cityToUpdate = getCity(city.getId());
		cityToUpdate.setName(city.getName());
		cityToUpdate.setSize(city.getSize());
		openSession.update(cityToUpdate);
		openSession.flush();
	}

	public City getCity(int id) {
		Session openSession = sessionFactory.openSession();
		City city = (City) openSession.get(City.class, id);
		openSession.flush();
		return city;
	}

	public void deleteCity(int id) {
		Session openSession = sessionFactory.openSession();
		City city = getCity(id);
		if (city != null)
			openSession.delete(city);
		openSession.flush();
	}

	public void addOrUpdateCity(City city) {
		Session openSession = sessionFactory.openSession();
		openSession.saveOrUpdate(city);
		openSession.flush();
	}

	@SuppressWarnings("unchecked")
	public List<City> getCities() {
		Session openSession = sessionFactory.openSession();
		return openSession.createQuery("from City").list();
	}

}
