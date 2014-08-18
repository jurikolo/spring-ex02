package org.talestats.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ModelAndView listOfVoteCities() {
		ModelAndView modelAndView = new ModelAndView("vote-list");
		
		List<City> cities = cityService.getCities();
		Map <City, Integer> mapCityVotes = new LinkedHashMap<>();
		Integer count = 0;
		for (City city : cities) {
			count = voteService.getVotesByCityId(city.getId()).size();
			mapCityVotes.put(city, count);
		}
		modelAndView.addObject("mapCityVotes", mapCityVotes);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/citylist")
	public @ResponseBody ModelAndView byParameter(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView("vote-citylist");
		City city = cityService.getCity(id);
		Map <City, List<HeroGuild>> cityToHeroMap = new HashMap<>();
		List <HeroGuild> heroesForCity = new ArrayList<>();
		HeroGuild heroForCity = new HeroGuild();
		
		for (Vote vote : voteService.getVotesByCityId(city.getId())) {
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
		modelAndView.addObject("cityToHeroMap", cityToHeroMap);
		
		return modelAndView;
	}

}