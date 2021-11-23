package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers_2021")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "firstname")
    private String firstName;
	
	@Column(name = "lastname")
    private String lastName;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "mobilenumber")
	@Schema(description = "Registred Ten Digit Mobile Number")
	private long mobileNumber;
	
	@Column(name = "birthdate")
	@DateTimeFormat(iso = ISO.DATE)
	@Schema(description = "The date of Birth in yyyy-mm-dd format")
    private LocalDate birthDate;



}
