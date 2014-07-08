package org.talestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.talestats.model.Hero;
import org.talestats.service.HeroService;

@Controller
@RequestMapping(value="/hero")
public class HeroController {

	@Autowired
	private HeroService heroService;
	
	@RequestMapping(value="/list")
	public ModelAndView listOfHeroes() {
		ModelAndView modelAndView = new ModelAndView("hero-list");
		
		List<Hero> heroes = heroService.getHeroes();
		modelAndView.addObject("heroes", heroes);
		
		return modelAndView;
	}

}
