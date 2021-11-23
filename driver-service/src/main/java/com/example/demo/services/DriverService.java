package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repos.DriverRepository;

import lombok.AllArgsConstructor;

import java.util.*;
import com.example.demo.entity.*;

@Service
public class DriverService {

	private DriverRepository repos;
	
	
	
	
    @Autowired
	public DriverService(DriverRepository repos) {
		super();
		this.repos = repos;
	}


	public List<Driver> getAll(){
		
		return this.repos.findAll();
	}
	
	
	public Driver addEntity(Driver entity) {
		
		return this.repos.save(entity);
	}
}
