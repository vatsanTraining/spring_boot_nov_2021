package com.example.demo.services;

import org.springframework.stereotype.Component;

import com.example.demo.StudentService;

public class CollegeStudent implements StudentService {

	@Override
	public boolean getResult(double markScored) {

		boolean result = false;
		
		if(markScored>80) {
			result = true;
		}
		
		return result;
	}

}
