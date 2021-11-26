package com.example.demo.utils;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@RestControllerAdvice
public class MyRestControllerAdvice {

	
	@ExceptionHandler(value = WebClientResponseException.class)
	public ResponseEntity<String> exceptionHandler(WebClientResponseException  ex) {
		

		return ResponseEntity.status(ex.getRawStatusCode())
				  .body(ex.getResponseBodyAsString());

	}
	


}
