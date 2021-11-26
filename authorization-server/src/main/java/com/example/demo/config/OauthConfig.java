package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OauthConfig extends AuthorizationServerConfigurerAdapter {

	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	AuthenticationManager manger;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		 security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("isAuthenticated()");
		 
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
		  .withClient("myclient")
		    .secret(encoder.encode("pass123"))
		     .scopes("read","write")
		      .authorizedGrantTypes("password")
		        .accessTokenValiditySeconds(3000)
		          .refreshTokenValiditySeconds(6000);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    
		 endpoints.authenticationManager(manger);
	}
	
	
}
