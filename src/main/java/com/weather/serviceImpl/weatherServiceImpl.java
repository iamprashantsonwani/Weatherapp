package com.weather.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.weather.DAO.AccuWeatherCurrentConditions;
import com.weather.DAO.AccuWeatherLocation;
import com.weather.service.weatherService;

@Service
public class weatherServiceImpl implements weatherService{

	@Value("${accuweather.api.key}")
	private String accuweatherApiKey;

	private final RestTemplate restTemplate;

	public weatherServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<AccuWeatherLocation> searchCities(String cityName) {
		String url = "http://dataservice.accuweather.com" + "/locations/v1/cities/search?apikey=" + accuweatherApiKey
				+ "&q=" + cityName;

		AccuWeatherLocation[] locations = restTemplate.getForObject(url, AccuWeatherLocation[].class);

		return Arrays.asList(locations);
	}

	public String getWeather(String cityKey, String cityName, String countryName, Model model) {
		String apiKeyParam = "apikey=" + accuweatherApiKey;
		try {
			String weatherUrl = "http://dataservice.accuweather.com/currentconditions/v1/" + cityKey;
			String url = weatherUrl + "?" + apiKeyParam;

			ResponseEntity<AccuWeatherCurrentConditions[]> weatherResponseEntity = restTemplate.getForEntity(url,
					AccuWeatherCurrentConditions[].class);

			if (weatherResponseEntity.getStatusCode().is2xxSuccessful()) {
				AccuWeatherCurrentConditions[] conditions = weatherResponseEntity.getBody();

				if (conditions != null && conditions.length > 0) {
					AccuWeatherCurrentConditions condition = conditions[0];

					model.addAttribute("cityName", cityName + ", " + countryName);
					model.addAttribute("temperature", condition.getTemperature().getMetric().getValue() + " °C");
					model.addAttribute("weatherCondition", condition.getWeatherText());
					model.addAttribute("weatherIcon", condition.getWeatherIcon());
					return "weather-info";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "error";
	}
}
