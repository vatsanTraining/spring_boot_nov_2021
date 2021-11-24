package com.example.demo.dtos;

import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private long mobileNumber;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;



}
