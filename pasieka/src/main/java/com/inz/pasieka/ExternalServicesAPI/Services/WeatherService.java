package com.inz.pasieka.ExternalServicesAPI.Services;

import com.inz.pasieka.ExternalServicesAPI.Helpers.WeatherDataForWidgetCombined;

public interface WeatherService {
    WeatherDataForWidgetCombined getWeatherData(String lat, String lon);
}
