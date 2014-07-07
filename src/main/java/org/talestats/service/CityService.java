package org.talestats.service;

import java.util.List;

import org.talestats.model.City;

public interface CityService {

    public void addCity(City city);
    public City getCity(int id);
    public void updateCity(City city);
    public void deleteCity(int id);
    public List<City> getCities();

}
