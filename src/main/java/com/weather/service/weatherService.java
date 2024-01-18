package com.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weather.DAO.AccuWeatherLocation;
import com.weather.serviceImpl.AccuWeatherClient;

@Service
public class weatherService {

	@Autowired
	private AccuWeatherClient accuWeatherClient;
	
	public  List<AccuWeatherLocation> searchCities(String city) {
		return accuWeatherClient.searchCities(city);
	}
	
}
