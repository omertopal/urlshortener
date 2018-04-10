package com.neueda.urlshortener.controller;

import java.util.ArrayList;
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
import com.neueda.urlshortener.data.entity.NeuedaUrlClick;
import com.neueda.urlshortener.data.model.NeuedaUrlClickModel;
import com.neueda.urlshortener.data.model.NeuedaUrlModel;
import com.neueda.urlshortener.data.model.NeuedaUrlStatsModel;
import com.neueda.urlshortener.data.service.IUrlClickService;
import com.neueda.urlshortener.data.service.IUrlService;
import com.neueda.urlshortener.error.NeuedaEmptyInputException;
import com.neueda.urlshortener.error.NeuedaInternalServerErrorException;
import com.neueda.urlshortener.error.NeuedaUrlNotFoundException;
import com.neueda.urlshortener.util.UrlConstants;
import com.neueda.urlshortener.util.UrlUtils;

@RestController
@RequestMapping("/stats/v1")
public class StatisticsControllerV1 {
	
private static final Logger log = Logger.getLogger(StatisticsControllerV1.class);
	
	@Autowired
	private IUrlService urlService;

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "clickInfo", method = RequestMethod.GET)
    public NeuedaUrlStatsModel getUrlClickStatisticsToday(@RequestParam(name="shortUrl", required=true, defaultValue="")  String shortUrl, HttpServletResponse resp) {  
		
		try{
			log.debug("StatisticsControllerV1.getUrlClickStatisticsToday method initiated with parameters: shortUrl="+ shortUrl);
			
			if(StringUtils.isEmpty(shortUrl)){
				throw new NeuedaEmptyInputException(UrlConstants.PARAM_SHORT_URL);
			}
			
			return urlService.getUrlStats(shortUrl);
			
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
			log.debug("StatisticsControllerV1.getUrlClickStatisticsToday method exited");
		}		
    }
}
