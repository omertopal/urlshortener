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
import com.neueda.urlshortener.data.model.NeuedaUrlModel;
import com.neueda.urlshortener.data.service.IUrlService;
import com.neueda.urlshortener.error.NeuedaEmptyInputException;
import com.neueda.urlshortener.error.NeuedaInternalServerErrorException;
import com.neueda.urlshortener.error.NeuedaUrlNotFoundException;
import com.neueda.urlshortener.util.UrlConstants;

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
			log.debug("ApiControllerV1.shortenUrl method initiated with parameters: longUrl="+ longUrl+", createUser="+ createUser );
			
			if(StringUtils.isEmpty(longUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_LONG_URL);
			}			
			if(StringUtils.isEmpty(urlTitle)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_URL_TITLE);
			}
			NeuedaUrlModel urlToBeCreated = new NeuedaUrlModel(UrlConstants.STRING_BLANK,longUrl,urlTitle,createUser);
			
			return urlService.createUrl(urlToBeCreated);						
			
		}catch (NeuedaEmptyInputException e) {
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}finally {
			log.debug("ApiControllerV1.shortenUrl method exited");
		}
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "expand", method = RequestMethod.GET)
    public String getExpandedUrl(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl, HttpServletResponse resp) {  
		
		try{
			log.debug("ApiControllerV1.getExpandedUrl method initiated with parameters: shortUrl="+ shortUrl );
			
			if(StringUtils.isEmpty(shortUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_SHORT_URL);
			}
			
			List<NeuedaUrl> urlsFound = urlService.findByShortUrl(shortUrl);
			
			if(urlsFound.size()==1)
				return urlsFound.get(0).getLongUrl();
			else{
				throw new NeuedaUrlNotFoundException(shortUrl);
			}
			
		}catch (NeuedaEmptyInputException e) {
			log.error(e);
			throw e;
		}catch (NeuedaUrlNotFoundException e) {
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}finally {
			log.debug("ApiControllerV1.getExpandedUrl method exited");
		}
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
    public NeuedaUrl saveUrlInfo(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl,
    						  @RequestParam(name="longUrl", required=true, defaultValue="")  String longUrl, 
    						  @RequestParam(name="urlTitle", required=true, defaultValue="")  String urlTitle, HttpServletResponse resp) {  
		
		try{
			log.debug("ApiControllerV1.saveUrlInfo method initiated with parameters: shortUrl="+ shortUrl +", longUrl="+ longUrl +", urlTitle="+ urlTitle);
			
			if(StringUtils.isEmpty(shortUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_SHORT_URL);
			}
			
			if(StringUtils.isEmpty(longUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_LONG_URL);
			}
			
			if(StringUtils.isEmpty(urlTitle)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_URL_TITLE);
			}
			
			NeuedaUrlModel urlToBeCreated = new NeuedaUrlModel(shortUrl,longUrl,urlTitle);
			return urlService.updateUrl(urlToBeCreated);
			
		}catch (NeuedaEmptyInputException e) {
			log.error(e);
			throw e;
		}catch (NeuedaUrlNotFoundException e) {
			log.error(e);
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}finally {
			log.debug("ApiControllerV1.saveUrlInfo method exited");
		}
    }
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "info", method = RequestMethod.GET)
    public NeuedaUrlModel getUrlInfo(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl, HttpServletResponse resp) {  
		
		try{
			log.debug("ApiControllerV1.getUrlInfo method initiated with parameters: shortUrl="+ shortUrl);
			
			if(StringUtils.isEmpty(shortUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_SHORT_URL);
			}
			
			return urlService.getUrlByShortUrl(shortUrl);
			
		}catch (NeuedaEmptyInputException e) {
			log.error(e);
			throw e;
		}catch (NeuedaUrlNotFoundException e) {
			log.error(e);
			throw e;
		}catch (Exception e) {
			log.error(e);
			throw new NeuedaInternalServerErrorException();
		}finally {
			log.debug("ApiControllerV1.getUrlInfo method exited");
		}
		
    }
		
}
