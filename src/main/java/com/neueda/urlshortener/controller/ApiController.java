package com.neueda.urlshortener.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.urlshortener.error.NeuedaForbiddenException;

@RestController
@RequestMapping("/api")
public class ApiController {
	 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "", method = RequestMethod.GET)
	public String badRequestApi() {				
		throw new NeuedaForbiddenException();        
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/v1", method = RequestMethod.GET)
	public String badRequestv1() {				
		throw new NeuedaForbiddenException();        
	}
}
