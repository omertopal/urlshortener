package com.neueda.urlshortener.data.model;

import java.util.Date;


public class NeuedaUrlClickModel {	
	
	private String ipLocation;
	
	private String ipAddress;
	
	private Date clickDate;
	
	@Override
	public String toString() {
		return new com.google.gson.Gson().toJson(this);
	}

	public String getIpLocation() {
		return ipLocation;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public Date getClickDate() {
		return clickDate;
	}

	public void setIpLocation(String ipLocation) {
		this.ipLocation = ipLocation;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setClickDate(Date clickDate) {
		this.clickDate = clickDate;
	}
}
