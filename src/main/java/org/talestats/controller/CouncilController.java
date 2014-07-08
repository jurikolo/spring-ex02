package org.talestats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.talestats.model.Council;
import org.talestats.service.CouncilService;

@Controller
@RequestMapping(value="/council")
public class CouncilController {

	@Autowired
	private CouncilService councilService;
	
	@RequestMapping(value="/list")
	public ModelAndView listOfCouncils() {
		ModelAndView modelAndView = new ModelAndView("council-list");
		
		List<Council> councils = councilService.getCouncils();
		modelAndView.addObject("councils", councils);
		
		return modelAndView;
	}

}
