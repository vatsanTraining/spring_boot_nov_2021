package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.repos.BookingRepository;

import lombok.AllArgsConstructor;

import java.util.*;
import com.example.demo.entity.*;

@Service
@AllArgsConstructor
public class BookingService {

	private BookingRepository repos;
	
		
	public BookingEntity findById(Long id) {
				
		return this.repos.findById(id)
				  .orElseThrow(() -> new RuntimeException("Element with Given Id Not Found"));
			
	}
   

	public List<BookingEntity> getAll(){
		
		return this.repos.findAll();
	}
	
	
	
	public BookingEntity addEntity(BookingEntity entity) {
		
		System.out.println(entity);

		Optional<BookingEntity> isAdded =this.repos.findById(entity.getBookingid());

		
		if(isAdded.isEmpty()) {
			this.repos.save(entity);

			System.out.println("IS Empty Check");
			return entity;

		} else {
			
			return null;
		}
		
		
	}
	
	 public Optional<BookingEntity> remove(BookingEntity entity) {
		   
		   Optional<BookingEntity> optional = Optional.empty();

		if(this.repos.existsById(entity.getBookingid())) {

		   this.repos.delete(entity);

		   optional = Optional.of(entity);
		}

		      return optional;  
		 }


	public BookingEntity update(BookingEntity entity) {
		
		return this.repos.save(entity);
	}

    
}
