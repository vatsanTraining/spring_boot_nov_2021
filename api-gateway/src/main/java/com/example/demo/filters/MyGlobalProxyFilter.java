package com.example.demo.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MyGlobalProxyFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
	
		
		boolean result =
				exchange.getRequest().getURI().
				        getRawPath().contains("v4");
				      
				   if(result) {
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				        return exchange.getResponse().setComplete();
				        }
				      
				return chain.filter(exchange);
	}

}
