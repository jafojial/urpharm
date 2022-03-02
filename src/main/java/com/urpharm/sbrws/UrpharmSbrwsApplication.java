package com.urpharm.sbrws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


/**
 * 
 * @author JAFOJIAL
 *
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Urpharm Spring Boot Rest Webservice", version = "1.0", description = "Spring Boot Rest Webservice to perform operation on a customer entity"))
public class UrpharmSbrwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrpharmSbrwsApplication.class, args);
	}

}
