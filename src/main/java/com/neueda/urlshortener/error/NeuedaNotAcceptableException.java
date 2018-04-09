package com.neueda.urlshortener.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neueda.urlshortener.util.UrlConstants;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NeuedaNotAcceptableException extends RuntimeException {
	
	private static final long serialVersionUID = 8190976119855660250L;
	public static final String errorMesage = "Opps, we couldn't find a link for the short URL you clicked.. \n Most Neueda links generally are "
    										+ UrlConstants.SHORT_URL_LENGTH + " characters , and only include letters and numbers";
    
	public NeuedaNotAcceptableException() {
		super(errorMesage);
    }
	
	
}
