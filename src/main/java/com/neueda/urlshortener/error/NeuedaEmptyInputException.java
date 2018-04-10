package com.neueda.urlshortener.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NeuedaEmptyInputException extends RuntimeException {
	
	private static final long serialVersionUID = -9180542551762900245L;
	public static final String errorInputNullInput = "Please provide param: ";
    
	public NeuedaEmptyInputException(String exception) {
		super(errorInputNullInput+exception);
    }
	

}
