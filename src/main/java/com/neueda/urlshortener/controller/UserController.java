package com.neueda.urlshortener.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.entity.NeuedaUrlClick;
import com.neueda.urlshortener.data.service.IUrlClickService;
import com.neueda.urlshortener.data.service.IUrlService;
import com.neueda.urlshortener.error.NeuedaInternalServerErrorException;
import com.neueda.urlshortener.error.NeuedaNotAcceptableException;
import com.neueda.urlshortener.util.DateUtils;
import com.neueda.urlshortener.util.UrlConstants;
import com.neueda.urlshortener.util.UrlUtils;

@RestController
@RequestMapping("")
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUrlService urlService;		
	
	@Autowired
	private IUrlClickService urlClickService;	
	
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    public void redirect(@PathVariable String shortUrl, HttpServletRequest request, HttpServletResponse response) {
        try{
        	log.debug("redirect method initiated with parameters: shortUrl"+ shortUrl);
        	
			if (!UrlUtils.isShortUrlValid(shortUrl)){			
				throw new NeuedaNotAcceptableException();
			}
			
			List<NeuedaUrl> urlsFound = urlService.findByShortUrl(shortUrl);
	        if(urlsFound.isEmpty()){
	        	throw new NeuedaNotAcceptableException();
	        }
	        if(urlsFound.size()!=1){
	        	throw new NeuedaInternalServerErrorException();
	        }	        
	        NeuedaUrl returnUrl = urlsFound.get(0);
	        if(returnUrl.getLongUrl().isEmpty()){
	        	throw new NeuedaNotAcceptableException();
	        }
	        
	        //add click to db
	        NeuedaUrlClick click = new NeuedaUrlClick();
	        click.setShortUrl(shortUrl);
	        click.setClickDate(DateUtils.getCurrentDate());
	        click.setIpAddress(request.getRemoteAddr());
	        click.setIpLocation(UrlConstants.DOMAIN_LOCALHOST);
	        urlClickService.insertClick(click);
	        
	        response.sendRedirect(returnUrl.getLongUrl());
        
        }catch (NeuedaNotAcceptableException e) {
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}
    }
}
