package com.example.demo.utils;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.model.ErrorHandler;

@RestControllerAdvice
public class MyRestControllerAdvice {

	
	@ExceptionHandler(value = Exception.class)
	public ErrorHandler globalExceptionHandler(Exception ex, WebRequest request) {
		

		return new ErrorHandler(ex.getMessage(), request.getDescription(false),LocalDateTime.now());
	}
	


}
