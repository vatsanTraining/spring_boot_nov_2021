package com.example.demo.filters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter {

		
	 
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new RuntimeException("Missing authorization information");
        }

        List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");

        

         if(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0)!=null) {
         

        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().
        	      header("Authorization", authorization.get(0)).build();
        	   
        	     return chain.filter(exchange.mutate().request(modifiedRequest).build());
         } else {
        	 
        	 return chain.filter(exchange);
         }
  
	}
	
}


