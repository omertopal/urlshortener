package com.neueda.urlshortener.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NeuedaUrlNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3843909388169657370L;
	public static final String errorMesage1 = "URL definition for shortUrl:";
    public static final String errorMesage2 = " not found in database";
    
	public NeuedaUrlNotFoundException(String exception) {
		super(errorMesage1 + exception + errorMesage2);
    }
	

}
