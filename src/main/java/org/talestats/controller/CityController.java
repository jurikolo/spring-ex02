package org.talestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.talestats.model.City;
import org.talestats.service.CityService;

@Controller
@RequestMapping(value = "/city")
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/list")
	public ModelAndView listOfCities() {
		ModelAndView modelAndView = new ModelAndView("city-list");

		List<City> cities = cityService.getCities();
		modelAndView.addObject("cities", cities);

		return modelAndView;
	}

}