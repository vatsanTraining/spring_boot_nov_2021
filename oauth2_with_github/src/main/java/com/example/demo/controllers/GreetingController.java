package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	
	@GetMapping(path = "/")
	public String hello() {
		
		return "Welcome Page";
	}
	
	@GetMapping(path = "/greet")
	public String greet() {
		
		return "Welcome to Social Login with github";
	}
	
}
