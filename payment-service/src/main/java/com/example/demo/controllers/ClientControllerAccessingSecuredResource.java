package com.example.demo.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/v4")

public class ClientControllerAccessingSecuredResource {

	@Autowired
	private WebClient client;

	
	@GetMapping(path = "/secured/client")
	public Mono<String> getAllDriversUsingSecurity(){
		

		return client
				 .get()
				   .uri("lb://DRIVER-SERVICE/api/v1/drivers")
				   .headers(h -> h.setBasicAuth("lanka","lanka"))
				     .retrieve()
				     .onStatus(HttpStatus::is5xxServerError, clientResponse ->
				        Mono.error(new RuntimeException()))
				      .bodyToMono(String.class);
	}

}

