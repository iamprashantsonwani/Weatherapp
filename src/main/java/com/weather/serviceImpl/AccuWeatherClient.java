package com.weather.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.weather.DAO.AccuWeatherLocation;

import java.util.Arrays;
import java.util.List;

@Component
public class AccuWeatherClient {

    @Value("${accuweather.api.url}")
    private String apiUrl;

    @Value("${accuweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public AccuWeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AccuWeatherLocation> searchCities(String cityName) {
        String url = apiUrl + "/locations/v1/cities/search?apikey=" + apiKey + "&q=" + cityName;

        AccuWeatherLocation[] locations = restTemplate.getForObject(url, AccuWeatherLocation[].class);

        return Arrays.asList(locations);
    }

    public String getWeatherInfo(String cityKey) {
        // Implement this method to get weather information based on the cityKey
        // Use the AccuWeather API to fetch weather data for the specified cityKey

        return "Weather information for cityKey: " + cityKey;
    }
}
