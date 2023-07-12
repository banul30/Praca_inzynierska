package com.inz.pasieka;

import com.inz.pasieka.ExternalServicesAPI.Helpers.WeatherDataForWidgetCombined;
import com.inz.pasieka.ExternalServicesAPI.Services.WeatherService;

public class MockWeatherService implements WeatherService {
    @Override
    public WeatherDataForWidgetCombined getWeatherData(String lat, String lon) {
        return new WeatherDataForWidgetCombined(10L, "mockCityName");
    }
}
