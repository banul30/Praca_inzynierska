package com.inz.pasieka.ExternalServicesAPI.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherCurrentWrapper {
//    public Weather weather;
//    public Wind wind;
//    public Main main;
    public Long id;
    public String name;
}

//class Weather {
//    public String main;
//    public String description;
//    public String icon;
//}
class Wind{
    public double speed;
    public int deg;

}
class Main {
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;
}


