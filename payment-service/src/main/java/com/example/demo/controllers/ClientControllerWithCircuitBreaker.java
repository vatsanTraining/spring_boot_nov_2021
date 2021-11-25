package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/v3")
public class ClientControllerWithCircuitBreaker {

	
	private WebClient client;
	
	private ReactiveResilience4JCircuitBreakerFactory factory;
	private ReactiveCircuitBreaker cbreaker;
	
	
	@Autowired
	public ClientControllerWithCircuitBreaker(@Qualifier("client") WebClient client,
			ReactiveResilience4JCircuitBreakerFactory factory) {
		super();
		this.client = client;
		this.factory = factory;
		this.cbreaker= this.factory.create("payment-service");

	}

	
	@GetMapping(path = "/registry/cb/client")
	public Mono<String> getAllDriversUsingRegistryWithLoadBalancer(){
		

		return this.cbreaker.run(client
				 .get()
				   .uri("lb://DRIVER-SERVICE/api/v1/drivers")
				     .retrieve()
				      .bodyToMono(String.class),ex -> Mono.just("fallBack"));

	}

}
