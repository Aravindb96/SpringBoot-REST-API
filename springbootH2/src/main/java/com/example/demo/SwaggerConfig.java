package com.example.demo;



import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.PathSelectors.*;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket EmployeeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.build()
				.apiInfo(apiInfo());
	}

	 private ApiInfo apiInfo() {
	        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of Employee API.", "API TOS", "Terms of service", "aravindcse1996@gmail.com", "v1.0", "www.facebook.com/aravindroid");
	        return apiInfo;
	    }
}
