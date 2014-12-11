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
import org.talestats.model.Guild;
import org.talestats.model.Hero;
import org.talestats.model.HeroExtra;
import org.talestats.service.CityService;
import org.talestats.service.CouncilService;
import org.talestats.service.GuildService;
import org.talestats.service.HeroService;

@Controller
@RequestMapping(value = "/hero")
public class HeroController {

	@Autowired
	private HeroService heroService;
	@Autowired
	private CityService cityService;
	@Autowired
	private CouncilService councilService;
	@Autowired
	private GuildService guildService;

	@RequestMapping(value = "/list")
	public ModelAndView listOfHeroes() {
		ModelAndView modelAndView = new ModelAndView("hero-list");

		List<Hero> heroes = heroService.getHeroes();
		List<City> cities = cityService.getCities();
		List<Council> councils = councilService.getCouncils();
		List<Guild> guilds = guildService.getGuilds();
		HeroExtra heroExtra = new HeroExtra();
		Map<Hero, HeroExtra> heroToHeroExtraMap = new HashMap<>();

		for (Hero hero : heroes) {
			heroExtra = new HeroExtra();
			for (City city : cities) {
				if (city.getId() == hero.getCityId()) {
					heroExtra.setCity(city);
					break;
				}
			}
			for (Council council : councils) {
				if (council.getId() == hero.getAllyId()) {
					heroExtra.setAlly(council);
					heroExtra.setAllyCity(cityService.getCity(council.getCityId()));
				}
				if (council.getId() == hero.getEnemyId()) {
					heroExtra.setEnemy(council);
					heroExtra.setEnemyCity(cityService.getCity(council.getCityId()));
				}
			}
			for (Guild guild : guilds) {
				if (guild.getId() == hero.getGuildId()) {
					heroExtra.setGuild(guild);
					break;
				}
			}
			heroToHeroExtraMap.put(hero, heroExtra);
		}

		modelAndView.addObject("heroToHeroExtraMap", heroToHeroExtraMap);

		modelAndView.addObject("heroesCount", heroes.size());

		return modelAndView;
	}

}
