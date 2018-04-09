package com.neueda.urlshortener.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class UserController {
			
	
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    public void redirect(@PathVariable String shortUrl, HttpServletResponse resp) throws Exception {
        final String url = "http://www.milliyet.com";
        if (url != null)
            resp.sendRedirect(url);
        else
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
