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

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addCity(City city) {
		getCurrentSession().save(city);
	}

	public void updateCity(City city) {
		City cityToUpdate = getCity(city.getId());
		cityToUpdate.setName(city.getName());
		cityToUpdate.setSize(city.getSize());
		getCurrentSession().update(cityToUpdate);
	}

	public City getCity(int id) {
		City city = (City) getCurrentSession().get(City.class, id);
		return city;
	}

	public void deleteCity(int id) {
		City city = getCity(id);
		if (city != null)
			getCurrentSession().delete(city);
	}

	@SuppressWarnings("unchecked")
	public List<City> getCities() {
		return getCurrentSession().createQuery("from City").list();
	}

}
