package com.weatherapp.myweatherapp.service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

@Service
public class WeatherService {

    @Autowired
    VisualcrossingRepository weatherRepo;

    public CityInfo forecastByCity(String city) {
        return weatherRepo.getByCity(city);
    }

    public String compareDaylightHours(String city1, String city2) {
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);

        if (cityInfo1 == null || cityInfo1.getCurrentConditions() == null ||
            cityInfo2 == null || cityInfo2.getCurrentConditions() == null) {
            return "Weather data unavailable for one or both cities.";
        }

        String sunrise1Str = cityInfo1.getCurrentConditions().getSunrise();
        String sunset1Str = cityInfo1.getCurrentConditions().getSunset();
        String sunrise2Str = cityInfo2.getCurrentConditions().getSunrise();
        String sunset2Str = cityInfo2.getCurrentConditions().getSunset();

        LocalTime sunrise1 = parseTime(sunrise1Str);
        LocalTime sunset1 = parseTime(sunset1Str);
        LocalTime sunrise2 = parseTime(sunrise2Str);
        LocalTime sunset2 = parseTime(sunset2Str);

        if (sunrise1 == null || sunset1 == null || sunrise2 == null || sunset2 == null) {
            return "Invalid sunrise or sunset time for one or both cities.";
        }

        Duration daylight1 = Duration.between(sunrise1, sunset1);
        Duration daylight2 = Duration.between(sunrise2, sunset2);

        return (daylight1.compareTo(daylight2) >= 0) ? city1 : city2;
    }

    private LocalTime parseTime(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null;
        }

        try {
            DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("HH:mm:ss");
            return LocalTime.parse(timeStr, formatterWithSeconds);
        } catch (Exception e1) {
            try {
                DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("HH:mm");
                return LocalTime.parse(timeStr, formatter24);
            } catch (Exception e2) {
                System.out.println("Error parsing time: " + timeStr);
                return null;
            }
        }
    }

    public String checkRainingCity(String city1, String city2) {
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);

        if (cityInfo1 == null || cityInfo1.getCurrentConditions() == null ||
            cityInfo2 == null || cityInfo2.getCurrentConditions() == null) {
            return "Weather data unavailable for one or both cities.";
        }

        String condition1 = cityInfo1.getCurrentConditions().getConditions();
        String condition2 = cityInfo2.getCurrentConditions().getConditions();

        // System.out.println("City 1: " + city1 + " - Condition: " + condition1);
        // System.out.println("City 2: " + city2 + " - Condition: " + condition2);

        boolean isRainingCity1 = condition1.toLowerCase().contains("rain");
        boolean isRainingCity2 = condition2.toLowerCase().contains("rain");

        if (isRainingCity1 && isRainingCity2) {
            return "It is currently raining in both " + city1 + " and " + city2;
        } else if (isRainingCity1) {
            return "It is currently raining in " + city1;
        } else if (isRainingCity2) {
            return "It is currently raining in " + city2;
        } else {
            return "It is not raining in either city.";
        }
    }
}
