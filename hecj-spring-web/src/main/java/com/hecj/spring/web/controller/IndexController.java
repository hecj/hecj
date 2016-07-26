package com.hecj.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value="/p/index",method=RequestMethod.GET)
	public String index(){
		
		System.out.println("我进来了");
		return "index";
	}
	
}
