package com.neueda.urlshortener.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.neueda.urlshortener.error.NeuedaEmptyInputException;
import com.neueda.urlshortener.error.NeuedaErrorDetails;
import com.neueda.urlshortener.error.NeuedaForbiddenException;
import com.neueda.urlshortener.error.NeuedaInternalServerErrorException;
import com.neueda.urlshortener.error.NeuedaNotAcceptableException;
import com.neueda.urlshortener.error.NeuedaUrlNotFoundException;

@ControllerAdvice
@RestController
public class NeuedaResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NeuedaUrlNotFoundException.class)
	public final ResponseEntity<NeuedaErrorDetails> handleUrlNotFoundException(NeuedaUrlNotFoundException ex, WebRequest request) {
		NeuedaErrorDetails errorDetails = new NeuedaErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NeuedaForbiddenException.class)
	public final ResponseEntity<NeuedaErrorDetails> handleForbiddenException(NeuedaForbiddenException ex, WebRequest request) {
		NeuedaErrorDetails errorDetails = new NeuedaErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(NeuedaNotAcceptableException.class)
	public final ResponseEntity<NeuedaErrorDetails> handleNotAcceptableException(NeuedaNotAcceptableException ex, WebRequest request) {
		NeuedaErrorDetails errorDetails = new NeuedaErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(NeuedaInternalServerErrorException.class)
	public final ResponseEntity<NeuedaErrorDetails> handleInternalServerErrorException(NeuedaInternalServerErrorException ex, WebRequest request) {
		NeuedaErrorDetails errorDetails = new NeuedaErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NeuedaEmptyInputException.class)
	public final ResponseEntity<NeuedaErrorDetails> handleInternalServerErrorException(NeuedaEmptyInputException ex, WebRequest request) {
		NeuedaErrorDetails errorDetails = new NeuedaErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}
	
}
