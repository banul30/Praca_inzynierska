package com.inz.pasieka.ExternalServicesAPI.dao.forecast;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecast{
    public int id;
    public String main;
    public String description;
    public String icon;
}