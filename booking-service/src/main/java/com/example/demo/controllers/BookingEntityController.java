package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.*;
import com.example.demo.entity.*;
import com.example.demo.services.BookingService;
@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class BookingEntityController {

	private BookingService service;
	
	
	@GetMapping(path = "/bookings")
	public List<BookingEntity> getAll(){
		
		return this.service.getAll();
	}
	
	@GetMapping(path = "/bookings/{bookingId}")
	public BookingEntity getById(@PathVariable("bookingId") long bookingId){
		
		return this.service.findById(bookingId);
	}
	 
	@PostMapping(path = "/bookings")
	public ResponseEntity<BookingEntity> addEntity(@RequestBody BookingEntity entity){
		
		
		BookingEntity addedObject = this.service.addEntity(entity);
		
		if(addedObject!=null) {
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}")
		          .buildAndExpand(entity.getBookingid())
		          .toUri();  

		return ResponseEntity.created(location).body(entity);  
		} else {
			
			 throw new RuntimeException("Resource With Id Already Exisits");
		}
				
	}
	
	
	@PutMapping(path = "/bookings")
	 public BookingEntity update(@RequestBody BookingEntity entity) {
	      return this.service.update(entity);
	 }

	@DeleteMapping(path = "/bookings")

	public ResponseEntity<BookingEntity> remove(@RequestBody BookingEntity entity) {

	BookingEntity deletedEntity = this.service.remove(entity).orElseThrow(()-> new RuntimeException("Element NOT FOUND"));

	     return ResponseEntity.ok().body(deletedEntity);

	}


		

	
}
