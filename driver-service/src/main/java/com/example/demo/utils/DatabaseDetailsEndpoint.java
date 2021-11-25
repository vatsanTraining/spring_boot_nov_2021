package com.example.demo.utils;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@WebEndpoint(id = "dbinfo")
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DatabaseDetailsEndpoint {

	
		private String url;
        private String username;
        
        
        @ReadOperation
        public String getDetails() {
        	
        	return this.url+"," +this.username;
        	
        }
		
}
