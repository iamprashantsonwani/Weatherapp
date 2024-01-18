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
	 
	
	//private final weatherService weatherservice;
	
	@GetMapping("/weather")
	public String getWeather(@RequestParam String city, Model model) {
		
		String accuweatherUrl = "http://dataservice.accuweather.com/locations/v1/cities/search";
        String apiKeyParam = "apikey=" + accuweatherApiKey;
        String cityParam = "q=" + city;
		
        String url = accuweatherUrl + "?" + apiKeyParam + "&" + cityParam;
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AccuWeatherLocation[]> responseEntity = restTemplate.getForEntity(url, AccuWeatherLocation[].class);
        //response is not picking up
        try{
        	if(responseEntity.getStatusCode().is2xxSuccessful()) {
            	AccuWeatherLocation[] locations = responseEntity.getBody();
            	
            	if(locations != null && locations.length > 0) {
            		AccuWeatherLocation location = locations[0];
            		
            		String weatherUrl = "http://dataservice.accuweather.com/currentconditions/v1/" + location.getKey();
                    url = weatherUrl + "?" + apiKeyParam;
                    
                    ResponseEntity<AccuWeatherCurrentConditions[]> weatherResponseEntity = restTemplate.getForEntity(url, AccuWeatherCurrentConditions[].class);
                    
                    if(weatherResponseEntity.getStatusCode().is2xxSuccessful()) {
                    	AccuWeatherCurrentConditions[] conditions = weatherResponseEntity.getBody();
                    	
                    	if(conditions != null && conditions.length > 0) {
                    		AccuWeatherCurrentConditions condition = conditions[0];
                    		
                    		model.addAttribute("cityName", location.getLocalizedName());
                    		model.addAttribute("temperature", condition.getTemperature().getMetric().getValue()+ " °C");
                    		model.addAttribute("weatherCondition", condition.getWeatherText());
                    		model.addAttribute("weatherIcon", condition.getWeatherIcon());
                    		return "weather-info";
                    	}
                    }
            	}
            }
        }
        	catch (Exception e) {
                e.printStackTrace();
                return "error";
            }
        
        
		return "error";
	}
	
	//@GetMapping("/search")
    /*public List<AccuWeatherLocation> searchCities(@RequestParam String city) {
        return weatherservice.searchCities(city);
    }*/
	/* public List<AccuWeatherLocation> searchCities(String cityName) {
        String url = "http://dataservice.accuweather.com + "/locations/v1/cities/search?apikey=" + accuweatherApiKey + "&q=" + cityName;

        AccuWeatherLocation[] locations = restTemplate.getForObject(url, AccuWeatherLocation[].class);

        return Arrays.asList(locations);
    }*/
}
