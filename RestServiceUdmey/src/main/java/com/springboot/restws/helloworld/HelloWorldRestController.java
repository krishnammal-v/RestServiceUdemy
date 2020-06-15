package com.springboot.restws.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {
	
	@Autowired
	private MessageSource bundleMessageSource;
	
@RequestMapping(method= RequestMethod.GET , path= "/hello-world")	
public String getHelloWorld(){
	return "HelloWorld";
}

@GetMapping(path="/hello-world-bean/{name}")
public HelloWorldBean getHelloWorldBean(@PathVariable String name){
	
	return new HelloWorldBean( name);
}

@GetMapping(path="/hello-world-i18n")
public String getHelloi18n(){
	
	return bundleMessageSource.getMessage("hello.message", null, LocaleContextHolder.getLocale());
	
}
}
