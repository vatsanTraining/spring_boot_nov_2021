package com.example.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.repos.DriverRepository;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.*;
import com.example.demo.entity.*;
import com.example.demo.model.DriverNotFoundException;

@Service
@AllArgsConstructor
public class DriverService {

	private DriverRepository repos;
	
		
	public Driver findById(Long id) {
				
		return this.repos.findById(id)
				  .orElseThrow(() -> new DriverNotFoundException("Element with Given Id Not Found"));
			
	}
   

	public List<Driver> getAll(){
		
		return this.repos.findAll();
	}
	
	public List<Driver> searchByFirstName(String firstName){
		
		return this.repos.findByFirstName(firstName);
	}
	
	
	public Page<Driver> searchByBirthDate(LocalDate startDate, LocalDate endDate, int page , int size){
		
		Pageable dobPage = PageRequest.of(page, size);
		
		
		return this.repos.findByBirthDateBetween(startDate, endDate, dobPage);
	
		
	}
	
	
	public Driver addEntity(Driver entity) {
		
		Optional<Driver> isAdded =this.repos.findById(entity.getId());
		
		if(isAdded.isEmpty()) {
			return entity;
		} else {
			
			return null;
		}
		
		
	}
}
