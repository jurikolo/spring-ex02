package org.talestats.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.talestats.model.City;
import org.talestats.model.HeroGuild;
import org.talestats.model.Vote;
import org.talestats.service.CityService;
import org.talestats.service.GuildService;
import org.talestats.service.HeroService;
import org.talestats.service.VoteService;

@Controller
@RequestMapping(value="/vote")
public class VoteController {

	@Autowired
	private CityService cityService;
	@Autowired
	private VoteService voteService;
	@Autowired
	private HeroService heroService;
	@Autowired
	private GuildService guildService;
	
	@RequestMapping(value="/list")
	public ModelAndView listOfVotes() {
		ModelAndView modelAndView = new ModelAndView("vote-list");
		
		List<City> cities = cityService.getCities();		
		
		Map <City, List<HeroGuild>> cityToHeroMap = new HashMap<>();
		List <HeroGuild> heroesForCity = new ArrayList<>();
		HeroGuild heroForCity = new HeroGuild();
		for (City city : cities) {
			heroesForCity = new ArrayList<>();
			for (Vote vote : voteService.getVotes()) {
				heroForCity = new HeroGuild();
				if (vote.getCityId() == city.getId()) {
					heroForCity.setHero(heroService.getHero(vote.getHeroId()));
					if (heroForCity.getHero().getGuildId() != 0) {
						heroForCity.setGuild(guildService.getGuild(heroForCity.getHero().getGuildId()));
					}
					heroesForCity.add(heroForCity);
				}
			}
			cityToHeroMap.put(city, heroesForCity);
		}
		
		modelAndView.addObject("cityToHeroMap", cityToHeroMap);
		return modelAndView;
	}

}