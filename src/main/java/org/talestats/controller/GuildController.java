package org.talestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.talestats.model.Guild;
import org.talestats.service.GuildService;

@Controller
@RequestMapping(value="/guild")
public class GuildController {

	@Autowired
	private GuildService guildService;
	
	@RequestMapping(value="/list")
	public ModelAndView listOfGuilds() {
		ModelAndView modelAndView = new ModelAndView("guild-list");
		
		List<Guild> guilds = guildService.getGuilds();
		modelAndView.addObject("guilds", guilds);
		
		return modelAndView;
	}

}
