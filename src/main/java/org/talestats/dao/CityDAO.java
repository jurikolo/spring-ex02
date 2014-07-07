package org.talestats.dao;

import java.util.List;

import org.talestats.model.City;

public interface CityDAO {

    public void addCity(City city);
    public City getCity(int id);
    public void updateCity(City city);
    public void deleteCity(int id);
    public List<City> getCities();
    
}