package com.neueda.urlshortener.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.service.IUrlService;
import com.neueda.urlshortener.error.NeuedaInternalServerErrorException;
import com.neueda.urlshortener.error.NeuedaNotAcceptableException;
import com.neueda.urlshortener.util.UrlConstants;
import com.neueda.urlshortener.util.UrlUtils;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	private IUrlService urlService;		
	
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    public void redirect(@PathVariable String shortUrl, HttpServletResponse resp) {
        try{
			if (!UrlUtils.isShortUrlValid(shortUrl)){			
				throw new NeuedaNotAcceptableException();
			}
			
			List<NeuedaUrl> urlsFound = urlService.findByShortUrl(shortUrl);
	        if(urlsFound.isEmpty()){
	        	throw new NeuedaNotAcceptableException();
	        }
	        
	        NeuedaUrl returnUrl = urlsFound.get(0);
	        if(returnUrl.getLongUrl().isEmpty()){
	        	throw new NeuedaNotAcceptableException();
	        }
	        resp.sendRedirect(returnUrl.getLongUrl());
        
        }catch (NeuedaNotAcceptableException e) {
			throw e;
		}catch (Exception e) {
			throw new NeuedaInternalServerErrorException(UrlConstants.STRING_BLANK);
		}
    }
}
