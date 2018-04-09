package com.neueda.urlshortener.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.neueda.urlshortener.error.NeuedaErrorDetails;
import com.neueda.urlshortener.error.NeuedaUrlNotFoundException;

@ControllerAdvice
@RestController
public class NeuedaResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NeuedaUrlNotFoundException.class)
	public final ResponseEntity<NeuedaErrorDetails> handleUserNotFoundException(NeuedaUrlNotFoundException ex, WebRequest request) {
		NeuedaErrorDetails errorDetails = new NeuedaErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
