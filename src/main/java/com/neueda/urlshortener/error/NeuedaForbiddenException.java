package com.neueda.urlshortener.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.FORBIDDEN)
public class NeuedaForbiddenException extends RuntimeException {
	
	private static final long serialVersionUID = -6720288978254367547L;
	
	public static final String errorMesage = " Please read Neueda URL Shortener API Documentation";    
    
	public NeuedaForbiddenException() {
		super(errorMesage);
    }
	

}
