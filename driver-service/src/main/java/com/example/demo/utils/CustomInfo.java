package com.example.demo.utils;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class CustomInfo implements InfoContributor {

	
	@Autowired
	ConfigurableApplicationContext ctx;
	
	@Override
	public void contribute(Builder builder) {

		int beanCount =  ctx.getBeanDefinitionCount();
		                
		Map<String,Object> details = new HashMap();
		
		details.put("BeanCount", beanCount);
		
		builder.withDetails(details);
	}

}
