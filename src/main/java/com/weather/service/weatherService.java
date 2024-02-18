package com.weather.service;

import java.util.List;
import org.springframework.ui.Model;
import com.weather.DAO.AccuWeatherLocation;

public interface weatherService {

	List<AccuWeatherLocation> searchCities(String city);

	String getWeather(String cityKey, String cityName, String countryName, Model model);
}
