package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.StudentService;

@Component
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Lazy
public class Student {

	// Field Injection
	
	//@Autowired
	private StudentService service;

//	 Constructor DI
	@Autowired
	public Student(@Qualifier("college") StudentService service) {
		super();
		System.out.println("Student Component Initialized");
		
		System.out.println(service.getClass().getName());
		this.service = service;
		
	}
	
	
	
	// Developer need not write this code
	// IoC container will create an instance 
	// college student and pass it to the class
	

//	public Student() {
//		super();
//		this.service = new CollegeStudent();
//		
//	}

	public boolean getResult(double mark) {
		
		
		return this.service.getResult(mark);
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	// Setter DI
//
//	@Autowired
//	public void setService(StudentService service) {
//		this.service = service;
//	}
	
	
	
}
