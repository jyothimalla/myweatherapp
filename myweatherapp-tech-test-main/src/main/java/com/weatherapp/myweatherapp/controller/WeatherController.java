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
    // TODO: given two city names, compare the length of the daylight hours and return the city with the longest day

    @GetMapping("/compare-daylight/{city1}/{city2}")
    public ResponseEntity<?> compareDaylight(@PathVariable("city1") String city1, 
                                             @PathVariable("city2") String city2) {
        try {
            String cityWithLongestDay = weatherService.compareDaylightHours(city1, city2);
            return ResponseEntity.ok("City with the longest daylight: " + cityWithLongestDay);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error comparing daylight hours.");
        }
    }
    // TODO: given two city names, check which city its currently raining in

    @GetMapping("/check-rain/{city1}/{city2}")
    public ResponseEntity<?> checkRainingCity(@PathVariable("city1") String city1, 
                                              @PathVariable("city2") String city2) {
        try {
            String rainingCity = weatherService.checkRainingCity(city1, city2);
            return ResponseEntity.ok(rainingCity);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error checking rain conditions.");
        }
    }
}
