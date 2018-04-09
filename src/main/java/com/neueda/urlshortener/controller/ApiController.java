package com.neueda.urlshortener.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("api")
public class ApiController {
	 
    @RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> badRequest() throws JSONException {				
        
    	List<JSONObject> entities = new ArrayList<JSONObject>();
        JSONObject entity = new JSONObject();
        entity.put("aa", "bb");
        entities.add(entity);
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
        
	}
}
