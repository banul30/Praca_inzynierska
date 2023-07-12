package com.inz.pasieka.ExternalServicesAPI.dao.forecast;

import com.inz.pasieka.ExternalServicesAPI.dao.forecast.ForecastWeatherObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
public class WeatherFiveDayForecastForPasieka {
public String cod;
public String message;
public String cnt;
public Collection<ForecastWeatherObject> list;
}

