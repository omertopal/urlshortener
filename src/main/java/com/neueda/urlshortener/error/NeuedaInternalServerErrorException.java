package com.neueda.urlshortener.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NeuedaInternalServerErrorException extends RuntimeException {
		
	private static final long serialVersionUID = 5032468483309020777L;
	public static final String errorMesage = "Oops something went wrong";
    
	public NeuedaInternalServerErrorException(String exception) {
		super(errorMesage + exception );
    }
	

}
