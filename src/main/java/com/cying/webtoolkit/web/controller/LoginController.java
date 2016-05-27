package com.cying.webtoolkit.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class LoginController {
  
    private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.debug("获取登录页");
		return "login/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("name")String name , @RequestParam("password")String password) {
		logger.debug("登录中,用户名{}，密码{}",name,password);
		return "redirect:index";
	}
}
