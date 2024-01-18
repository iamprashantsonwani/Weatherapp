function searchCities(){
	var cityName = $("#cityInput").val();
	
	$.get("/search?city=" +cityName,function(data){
		displayCityList(data);
	});
}

function displayCityList(cities) {
	var cityListHtml = "<ul>";
	cities.forEach(function(city){
		cityListHtml += "<li><a href='#' onclick='getWeather(\"" + city.Key + "\")'>" + city.LocalizedName + "</a></li>";
    });
    cityListHtml += "</ul>";
	
	$("cityList").html(cityListHtml);
}

function getWeather() {
	var cityName = document.getElementById("cityInput").value;
	
	localStorage.setItem("lastCity",cityName);
	
	$.get("/weather?city=" + cityName, function(data){
		$("#weatherInfo").html(data);
	});
}

$(document).ready(function() {
	var storedCity = localStorage.getItem("lastCity");
	
	if(storedCity){
		document.getElementById("cityInput").value = storedCity;
		getWeather();
	}
})