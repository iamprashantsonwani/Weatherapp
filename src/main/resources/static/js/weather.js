$(document).ready(function() {
	$("#cityInput").on('input', function() {
		var cityName = $(this).val();
		if (cityName.length >= 3) {
			$.get("/search?city=" + cityName)
				.done(function(data) {
					displayCitySuggestions(data);
				})
				.fail(function(jqXHR, textStatus, errorThrown) {
					console.error("Error fetching city data:", errorThrown);
				});
		} else {
			$("#citySuggestions").empty();
		}
	});

	// When a suggestion is clicked, fetch weather data for the selected city
	$(document).on('click', '.suggestion', function() {
		var cityKey = $(this).data('citykey');
		var cityName = $(this).data('city');
		var country = $(this).data('country');
		getWeather(cityKey, cityName, country);
		$("#citySuggestions").empty();
	});

	// Retrieve last selected city from localStorage and display weather
	var storedCity = localStorage.getItem("lastCity");
	if (storedCity) {
		$("#cityInput").val(storedCity);
		$("#cityInput").trigger('input');
	}
});

function displayCitySuggestions(cities) {
	var citySuggestions = $("#citySuggestions");
	citySuggestions.empty();
	cities.forEach(function(city) {
		citySuggestions.append("<div class='suggestion z-50' data-citykey='" + city.Key + "' data-city='" + city.LocalizedName + "' data-country='" + city.Country.LocalizedName + "'>" + city.LocalizedName + ", " + city.Country.LocalizedName + " </div>");
	});
}

function getWeather(cityKey, cityName, country) {
	$.get("/weather", {
		cityKey: cityKey,
		cityName: cityName,
		countryName: country
	})
		.done(function(data) {
			$("#weatherInfo").html(data);
			$("#weatherInfo").removeClass("hidden").addClass("show");
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.error("Error fetching weather data:", errorThrown);
		});
}
