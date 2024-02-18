package com.weather.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

	@JsonProperty("LocalizedName")
    private String countryName;

	public String getLocalizedName() {
		return countryName;
	}

	public void setLocalizedName(String countryName) {
		this.countryName = countryName;
	}	
}
