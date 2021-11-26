package com.example.demo.controllers;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SampleController {

	
	@Value("${custom.message}")
	private String message;
	
	@GetMapping(path = "/message")
	public String getMessage() {
		
		return this.message;
		
	}
}
