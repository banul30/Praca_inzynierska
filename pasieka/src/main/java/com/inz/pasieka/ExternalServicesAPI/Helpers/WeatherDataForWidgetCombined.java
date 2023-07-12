package com.inz.pasieka.ExternalServicesAPI.Helpers;

import lombok.Getter;

@Getter
public class WeatherDataForWidgetCombined {
    public Long cityId;
    public String cityName;

    public WeatherDataForWidgetCombined(Long cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }
}
