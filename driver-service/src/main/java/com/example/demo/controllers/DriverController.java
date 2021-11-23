package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.services.DriverService;

import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.*;
import com.example.demo.entity.*;
@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class DriverController {

	private DriverService service;
	
	
	@GetMapping(path = "/drivers")
	public List<Driver> getAll(){
		
		return this.service.getAll();
	}
	
	@GetMapping(path = "/drivers/{id}")
	public Driver getById(@PathVariable("id") Long id){
		
		return this.service.findById(id);
	}
	
	
	@PostMapping(path = "/drivers")
	public ResponseEntity<Driver> addEntity(@RequestBody Driver driver){
		
		Driver addedObject = this.service.addEntity(driver);
		
		if(addedObject!=null) {
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}")
		          .buildAndExpand(driver.getId())
		          .toUri();  

		return ResponseEntity.created(location).body(driver);  
		} else {
			
			 throw new RuntimeException("Resource With Id Already Exisits");
		}
				
	}
	
	
	
}
