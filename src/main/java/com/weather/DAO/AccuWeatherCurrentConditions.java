package com.weather.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccuWeatherCurrentConditions {
	
	@JsonProperty("Temperature")
	private Temperature temperature;
	
	@JsonProperty("WeatherText")
    private String weatherText;
	
	@JsonProperty("WeatherIcon")
    private String weatherIcon;
    
    
	public Temperature getTemperature() {
		return temperature;
	}
	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}
	public String getWeatherText() {
		return weatherText;
	}
	public void setWeatherText(String weatherText) {
		this.weatherText = weatherText;
	}
	public String getWeatherIcon() {
		return weatherIcon;
	}
	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}   
}
