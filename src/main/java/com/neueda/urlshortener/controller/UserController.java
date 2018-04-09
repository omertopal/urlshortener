package com.neueda.urlshortener.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.service.IUrlService;
import com.neueda.urlshortener.util.UrlUtils;

@Controller
@RequestMapping("")
public class UserController {
	
	@Autowired
	private IUrlService urlService;		
	
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    public void redirect(@PathVariable String shortUrl, HttpServletResponse resp) throws Exception {
        
		if (!UrlUtils.isShortUrlValid(shortUrl)){			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
            
		List<NeuedaUrl> urlsFound = urlService.findByShortUrl(shortUrl);
        if(urlsFound.size()!=1){
        	resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        NeuedaUrl returnUrl = urlsFound.get(0);
        if(returnUrl.getLongUrl().isEmpty()){
        	resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        resp.sendRedirect(returnUrl.getLongUrl());
    }
}
