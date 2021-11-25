package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder builRef(){
		
		return WebClient.builder();
	}
	
	@Bean
	public WebClient restClient(@Qualifier("builRef") WebClient.Builder builRef) {
		
		return builRef.build();
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder builderRef(){
		
		return WebClient.builder();
	}
	
	@Bean
	@Primary
	public WebClient client(WebClient.Builder builderRef) {
		
		return builderRef.build();
	}
}
