package com.weather.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metric {
	
	@JsonProperty("Value")
    private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}