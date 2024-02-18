package com.weather.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccuWeatherLocation {
	
	@JsonProperty("Key")
	private String key;
	
	@JsonProperty("LocalizedName")
    private String localizedName;
	
	@JsonProperty("Country")
    private Country country;
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLocalizedName() {
		return localizedName;
	}
	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}
}
