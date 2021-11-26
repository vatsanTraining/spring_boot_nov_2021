package com.example.demo.controllers;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import jdk.internal.org.jline.utils.Log;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@RestController
@AllArgsConstructor
@Slf4j
public class ClientControllerWithRetry {

	
	private  WebClient client;
	
	
	@GetMapping(path = "/orders")
	public Mono<String> getData() {
	    return client.get()
	      .uri("http://localhost:8080/orders")
	      .retrieve()
	      .bodyToMono(String.class)
	      .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(100))
	    		  .doAfterRetry(signal -> Log.info(signal.totalRetriesInARow())));
	}


	@GetMapping(path = "/orders/fallback")
	public Mono<String> getDataWithFallBack() {


		return client.get()
	      .uri("http://localhost:8080/orders")
	      .retrieve()
	      .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new RuntimeException("Server error"+response.rawStatusCode())))
	      .bodyToMono(String.class)
	      .retryWhen(Retry.backoff(3, Duration.ofSeconds(5))
	          .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
	              throw new RuntimeException("External Service failed to process after max retries"+ HttpStatus.SERVICE_UNAVAILABLE.value());
	          }));

	}
}



