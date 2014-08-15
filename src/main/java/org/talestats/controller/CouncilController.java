package org.talestats.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.talestats.model.City;
import org.talestats.model.Council;
import org.talestats.service.CityService;
import org.talestats.service.CouncilService;

@Controller
@RequestMapping(value="/council")
public class CouncilController {

	@Autowired
	private CouncilService councilService;
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="/list")
	public ModelAndView listOfCouncils() {
		ModelAndView modelAndView = new ModelAndView("council-list");
		
		List<Council> councils = councilService.getCouncils();
		List<City> cities = cityService.getCities();
		Map <Council, City> councilToCityMap = new HashMap<>();
		for (Council council : councils) {
			for (City city : cities) {
				if (city.getId() == council.getCityId()) {
					councilToCityMap.put(council, city);
				}
			}
		}
		modelAndView.addObject("councilToCityMap", councilToCityMap);
		return modelAndView;
	}

}
