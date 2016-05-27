package com.cying.webtoolkit.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
//@RequestMapping("/home")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		
		String formattedDate = dateFormat.format(date);
		logger.debug(formattedDate);
		
		return "index";
	}
	
	@RequestMapping(value = "/uploadPage", method = RequestMethod.GET)
	public String uploadPage() {
		System.out.println("ÉÏ´«Ò³");
		return "cying/multi_upload";
	}
	
	
}
