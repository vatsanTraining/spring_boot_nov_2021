package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;

import com.example.demo.model.Student;
import com.example.demo.services.CollegeStudent;
import com.example.demo.services.HighSchoolStudent;
import com.example.demo.utils.StudentCreationCondition;

@SpringBootApplication
public class DepenxecyInjectionApplication {

	
	
	public static void main(String[] args) {
	ConfigurableApplicationContext ctx	 =SpringApplication.run(DepenxecyInjectionApplication.class, args);
	
	     Student ram = ctx.getBean(Student.class);
	     
	     // Gets the Implementation Class of the ConfigurableApplicationContext
	     
	     System.out.println(ctx.getClass().getName());
	     
	     
	     System.out.println("is Student Pass :="  +ram.getResult(60));
	     
	     System.out.println(" Is High school Bean present:="+ctx.containsBean("highSchool"));
	     
//	     
//	     
//	     System.out.println("is Singleton:= " +ctx.isSingleton("student"));
//	     
//	     System.out.println("is Proptype:= " +ctx.isPrototype("student"));
//	     
	     
	     
	     ctx.close();
	
	}

	
	// Factory Method
	
	@Bean
	public StudentService college() {
		
		return new CollegeStudent();
	}
	
	
//	@Bean
//	@Primary
//	public StudentService highSchool() {
//		
//		return new HighSchoolStudent();
//	}
	
	
//	@Bean
//	@ConditionalOnProperty(name = "spring.application.name",havingValue = "diproject")
//	public StudentService highSchool() {
//		
//		System.out.println("High School Factory ");
//		return new HighSchoolStudent();
//	}
	
	@Bean
	@Conditional(StudentCreationCondition.class)
	public StudentService highSchool() {
		
		System.out.println("High School Factory ");
		return new HighSchoolStudent();
	}
}
