package com.weather.DAO;

public class WeatherResponse {
	private AccuWeatherLocation[] locations;

	public AccuWeatherLocation[] getLocations() {
		return locations;
	}

	public void setLocations(AccuWeatherLocation[] locations) {
		this.locations = locations;
	}
}
