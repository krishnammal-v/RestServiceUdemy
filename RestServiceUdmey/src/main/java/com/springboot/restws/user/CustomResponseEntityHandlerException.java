package com.springboot.restws.user;



import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityHandlerException extends ResponseEntityExceptionHandler{
	
	ExceptionResponseFormat exResponseFmt;
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)
			throws Exception {
		
		exResponseFmt  = new ExceptionResponseFormat(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(exResponseFmt, HttpStatus.BAD_REQUEST);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		exResponseFmt  = new ExceptionResponseFormat(new Date(), "", ex.getBindingResult().getFieldError().toString());
		return new ResponseEntity<Object>(exResponseFmt, HttpStatus.BAD_REQUEST);
	}
	
}
