package com.neueda.urlshortener.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.service.IUrlService;
import com.neueda.urlshortener.util.UrlUtils;

@Controller
@RequestMapping("api/v1")
public class ApiControllerV1 {	
	
	@Autowired
	private IUrlService urlService;
	
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "shorten", method = RequestMethod.GET)
    public NeuedaUrl shortenUrl(@RequestParam(name="longUrl", required=true, defaultValue="")  String longUrl,
    							@RequestParam(name="createUser", required=true, defaultValue="")  String createUser,HttpServletResponse resp) throws Exception {  				
		
		String shortUrl = UrlUtils.shortenUrl(longUrl);
		NeuedaUrl urltoCreate = new NeuedaUrl();
		urltoCreate.setShortUrl(shortUrl);
		urltoCreate.setLongUrl(longUrl);
		urltoCreate.setCreateUser(createUser);
		NeuedaUrl urlInserted= urlService.insertUrl(urltoCreate);
		    
		return urlInserted;        
    }
	
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "info", method = RequestMethod.GET)
    public List<NeuedaUrl> getUrlInfo(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl, HttpServletResponse resp) throws Exception {  
		
		List<NeuedaUrl> urlList = urlService.findByShortUrl(shortUrl);
				     
		return urlList;        
    }
}
