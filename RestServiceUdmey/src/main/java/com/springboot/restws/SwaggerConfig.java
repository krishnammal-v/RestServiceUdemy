package com.springboot.restws;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	  public static final Contact DEFAULT_CONTACT = new Contact("", "", "");
	  public static final ApiInfo API_DEFAULT_INFO = new ApiInfo("Sample Api Documentation", "Sample Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	  



	@Bean
	public Docket api(){
		
		 HashSet<String> API_PRODUCES_AND_CONSUMES = new HashSet<String>();
		 API_PRODUCES_AND_CONSUMES.add("application/json");
		 API_PRODUCES_AND_CONSUMES.add("application/xml");

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_DEFAULT_INFO).produces(API_PRODUCES_AND_CONSUMES).consumes(API_PRODUCES_AND_CONSUMES);
	}

}
