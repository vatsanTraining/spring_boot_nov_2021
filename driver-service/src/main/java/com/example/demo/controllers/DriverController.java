package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.services.DriverService;

import java.net.URI;
import java.util.*;
import com.example.demo.entity.*;
@RestController
@RequestMapping(path = "/api/v1")
public class DriverController {

	@Autowired
	private DriverService service;
	
	
	@GetMapping(path = "/drivers")
	public List<Driver> getAll(){
		
		return this.service.getAll();
	}
	
	@PostMapping(path = "/drivers")
	public ResponseEntity<Driver> addEntity(@RequestBody Driver driver){
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}")
		          .buildAndExpand(driver.getId())
		          .toUri();  

		return ResponseEntity.created(location).body(driver);  

				
	}
	
	
	
}
