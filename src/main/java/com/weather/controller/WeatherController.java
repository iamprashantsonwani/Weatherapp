package com.weather.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.weather.DAO.AccuWeatherCurrentConditions;
import com.weather.DAO.AccuWeatherLocation;
import com.weather.service.weatherService;

@Controller
public class WeatherController {

	@Value("${accuweather.api.key}")
	private String accuweatherApiKey;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@Autowired
	private weatherService weatherService;

	@GetMapping("/search")
	public ResponseEntity<List<AccuWeatherLocation>> searchCities(@RequestParam String city) {
		List<AccuWeatherLocation> cities = weatherService.searchCities(city);
		return ResponseEntity.ok(cities);
	}

	public String getWeather(@RequestParam String cityKey, @RequestParam String cityName,
			@RequestParam String countryName, Model model) {
		return weatherService.getWeather(cityKey, cityName, countryName, model);
	}

}
