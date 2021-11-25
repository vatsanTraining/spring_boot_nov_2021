package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dtos.Driver;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/v1")
public class ClientControllerWithOutLoadBalancer {

	
	
	@Autowired
	@Qualifier("restClient")
	private WebClient restClient;
	
	@GetMapping(path = "/client")
	public Mono<Driver[]> getAllDrivers(){
		
		return restClient
				 .get()
				   .uri("http://localhost:3030/api/v1/drivers")
				     .retrieve()
				      .bodyToMono(Driver[].class);

	}
	


}
