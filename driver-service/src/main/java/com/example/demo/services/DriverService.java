package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.repos.DriverRepository;

import lombok.AllArgsConstructor;

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
	
	
	public Driver addEntity(Driver entity) {
		
		Optional<Driver> isAdded =this.repos.findById(entity.getId());
		
		if(isAdded.isEmpty()) {
			return entity;
		} else {
			
			return null;
		}
		
		
	}
}
