package com.neueda.urlshortener.data.service;

import java.util.List;

import com.neueda.urlshortener.data.entity.NeuedaUrlClick;

public interface IUrlClickService {
	
	public List<NeuedaUrlClick> findByShortUrl(String shortUrl) throws Exception;
	
	public NeuedaUrlClick insertClick(NeuedaUrlClick click) throws Exception;
	
}
