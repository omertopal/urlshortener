package com.neueda.urlshortener.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.urlshortener.data.entity.NeuedaUrl;
import com.neueda.urlshortener.data.service.IUrlService;
import com.neueda.urlshortener.error.NeuedaEmptyInputException;
import com.neueda.urlshortener.error.NeuedaInternalServerErrorException;
import com.neueda.urlshortener.error.NeuedaUrlNotFoundException;
import com.neueda.urlshortener.util.DateUtils;
import com.neueda.urlshortener.util.UrlConstants;
import com.neueda.urlshortener.util.UrlUtils;

@RestController
@RequestMapping("/api/v1")
public class ApiControllerV1 {	
	private static final Logger log = Logger.getLogger(ApiControllerV1.class);
	
	@Autowired
	private IUrlService urlService;
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "shorten", method = RequestMethod.POST)
    public NeuedaUrl shortenUrl(@RequestParam(name="longUrl", required=true, defaultValue="")  String longUrl,
    							@RequestParam(name="urlTitle", required=true, defaultValue="")  String urlTitle,
    							@RequestParam(name="createUser", required=true, defaultValue="")  String createUser,HttpServletResponse resp) {  				
		
		try{
			log.debug("shortenUrl method initiated with parameters: longUrl="+ longUrl+"createUser="+ createUser );
			
			if(StringUtils.isEmpty(longUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_LONG_URL);
			}			
			
			if(StringUtils.isEmpty(urlTitle)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_URL_TITLE);
			}
			
			boolean isGeneratedShortUrlUnique = false;
			String generatedShortUrl = UrlConstants.STRING_BLANK;
			
			NeuedaUrl urlToBeCreated = new NeuedaUrl();		
			urlToBeCreated.setLongUrl(longUrl);
			urlToBeCreated.setUrlTitle(urlTitle);
			urlToBeCreated.setCreateUser(createUser);
			urlToBeCreated.setCreateDate(DateUtils.getCurrentDate());
			
			while(!isGeneratedShortUrlUnique){
				generatedShortUrl = UrlUtils.generateShortUrl();
				//check if this short url already exists in db
				List<NeuedaUrl> urlsFound = urlService.findByShortUrl(generatedShortUrl);
				if(urlsFound.isEmpty())
					isGeneratedShortUrlUnique = true;
			}
			urlToBeCreated.setShortUrl(generatedShortUrl);
			
			NeuedaUrl urlInserted= urlService.insertUrl(urlToBeCreated);
			return urlInserted;						
			
		}catch (NeuedaEmptyInputException e) {
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}finally {
			log.debug("shortenUrl method exited");
		}
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "expand", method = RequestMethod.GET)
    public String getExpandedUrl(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl, HttpServletResponse resp) {  
		
		try{
			List<NeuedaUrl> urlsFound = urlService.findByShortUrl(shortUrl);
			if(urlsFound.size()==1)
				return urlsFound.get(0).getLongUrl();
			else{
				throw new NeuedaUrlNotFoundException(shortUrl);
			}
		}catch (NeuedaUrlNotFoundException e) {
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
    public NeuedaUrl saveUrlInfo(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl,
    						  @RequestParam(name="longUrl", required=true, defaultValue="")  String longUrl, 
    						  @RequestParam(name="urlTitle", required=true, defaultValue="")  String urlTitle, HttpServletResponse resp) {  
		
		try{
			if(StringUtils.isEmpty(shortUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_SHORT_URL);
			}
			
			if(StringUtils.isEmpty(longUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_LONG_URL);
			}
			
			if(StringUtils.isEmpty(urlTitle)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_URL_TITLE);
			}
			
			List<NeuedaUrl> urlsFound = urlService.findByShortUrl(shortUrl);
			if(urlsFound.size()==1){
				NeuedaUrl urlTobeEdited = urlsFound.get(0);
				urlTobeEdited.setLongUrl(longUrl);	
				urlTobeEdited.setUrlTitle(urlTitle);
				NeuedaUrl urlEdited = urlService.saveUrl(urlTobeEdited);
				return urlEdited;
			}else{
				throw new NeuedaUrlNotFoundException(shortUrl);
			}
		}catch (NeuedaEmptyInputException e) {
			throw e;
		}catch (NeuedaUrlNotFoundException e) {
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "info", method = RequestMethod.GET)
    public NeuedaUrl getUrlInfo(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl, HttpServletResponse resp) {  
		
		try{
			List<NeuedaUrl> urlsFound = urlService.findByShortUrl(shortUrl);
			if(urlsFound.size()==1)
				return urlsFound.get(0);
			else{
				throw new NeuedaUrlNotFoundException(shortUrl);
			}
		}catch (NeuedaUrlNotFoundException e) {
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}
    }
		
}
