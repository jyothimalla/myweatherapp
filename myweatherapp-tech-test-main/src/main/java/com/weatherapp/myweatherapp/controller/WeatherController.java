package com.weatherapp.myweatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.weatherapp.myweatherapp.service.WeatherService;

@Controller
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/forecast/{city}")
    public ResponseEntity<String> forecastByCity(@PathVariable("city") String city) {
        return ResponseEntity.ok(weatherService.forecastByCity(city).toString());
    }

    @GetMapping("/compare-daylight/{city1}/{city2}")
    public ResponseEntity<String> compareDaylight(@PathVariable("city1") String city1, 
                                                  @PathVariable("city2") String city2) {
        String cityWithLongestDay = weatherService.compareDaylightHours(city1, city2);
        return ResponseEntity.ok("City with the longest daylight: " + cityWithLongestDay);
    }

    @GetMapping("/check-rain/{city1}/{city2}")
    public ResponseEntity<String> checkRainingCity(@PathVariable("city1") String city1, 
                                                   @PathVariable("city2") String city2) {
        String rainingCity = weatherService.checkRainingCity(city1, city2);
        return ResponseEntity.ok(rainingCity);
    }
}
