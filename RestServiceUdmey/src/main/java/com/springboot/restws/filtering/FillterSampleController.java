package com.springboot.restws.filtering;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FillterSampleController {

	@GetMapping(path="/filtering" )
	public MappingJacksonValue retrieveSomeBean(){
		
		SomeBean someBean = new SomeBean("val1","val2","val3");
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		Set<String> properties = new HashSet<>();
		properties.add("field2");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(properties );
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );

		mapping.setFilters(filters );
		
		return mapping;
	}
}
