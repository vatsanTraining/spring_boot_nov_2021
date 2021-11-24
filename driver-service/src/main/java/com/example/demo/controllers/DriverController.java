package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.services.DriverService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;
import com.example.demo.entity.*;
@RestController
@RequestMapping(path = "/api/v1")
//@AllArgsConstructor
public class DriverController {

	@Autowired
	private DriverService service;
	
	
	@Value("${server.port}")
	private String serverPort;
	
	
//	@GetMapping(path = "/drivers")
//	public List<Driver> getAll(){
//		
//		return this.service.getAll();
//	}
	
	@GetMapping(path = "/drivers")
	public String getAll(){
		
		return this.service.getAll().toString()+"=:="+serverPort;
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
	
	
	@PutMapping(path = "/drivers")
	 public Driver update(@RequestBody Driver entity) {
	      return this.service.update(entity);
	 }

	@DeleteMapping(path = "/drivers")

	public ResponseEntity<Driver> remove(@RequestBody Driver entity) {

	Driver driver = this.service.remove(entity).orElseThrow(()-> new RuntimeException("Element NOT FOUND"));

	     return ResponseEntity.ok().body(driver);

	}


	@PatchMapping(path = "/drivers/update/{id}/{latestNumber}")
	public ResponseEntity<String> updateMobileNumber(@PathVariable("id") long id, 
			@PathVariable("latestNumber") long latestNumber) {

		this.service.updateMobileNumber(id, latestNumber);

	 return  ResponseEntity.ok().body("Updated MobileNumber ");
	}

	@GetMapping(path = "/drivers/{id}")
	@Operation(description = "Fetches Driver Details By Id")
	public Driver getById(
			@Parameter(description = "Three digit Unique Id of the Driver",example = "101",required = true) 
			@PathVariable("id") Long id){
		
		return this.service.findById(id);
	}

	
	@GetMapping(path = "/drivers/srch/firstname/{name}")
	@Operation(description = "Fetches Driver Details By First Name")
	public List<Driver> getByFirstName(
			@Parameter(description = "First Name of The Drive",required = true) 
			@PathVariable("name") String name){
		
		return this.service.searchByFirstName(name);
	}
	
	@GetMapping(path="/drivers/srch/birthdate")
	public List<Driver> searchByBirthDate(
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate, 
			@RequestParam int page , 
			@RequestParam(defaultValue = "2") int size){
		
		List<Driver> blankList = new ArrayList();
		
		Page<Driver> driverPage= this.service.searchByBirthDate(startDate, endDate, page, size);
		
		     if(driverPage.hasContent()) {
		    	 return driverPage.getContent();
		     } else {
		    	 return blankList;
		     }
		
		
	}
	

	

	
}
