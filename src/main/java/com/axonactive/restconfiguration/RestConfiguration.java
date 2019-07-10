package com.axonactive.restconfiguration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("rest")
public class RestConfiguration extends Application {
	
	public RestConfiguration() {
		BeanConfig beanConfig=new BeanConfig();
		beanConfig.setSchemes(new String[] {"http"});
		beanConfig.setBasePath("demo/rest");
		beanConfig.setResourcePackage(RestConfiguration.class.getPackage().getName());
		beanConfig.setScan(true);
		
	}
	
}
