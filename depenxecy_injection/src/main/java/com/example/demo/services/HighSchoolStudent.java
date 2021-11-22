package com.example.demo.services;

import com.example.demo.StudentService;

public class HighSchoolStudent implements StudentService {

	@Override
	public boolean getResult(double markScored) {
		boolean result = false;
		
		if(markScored>70) {
			result = true;
		}
		
		return result;
	
	}

}
