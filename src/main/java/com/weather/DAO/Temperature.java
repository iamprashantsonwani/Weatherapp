package com.weather.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
	
	@JsonProperty("Metric")
    private Metric metric;

	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}
}
