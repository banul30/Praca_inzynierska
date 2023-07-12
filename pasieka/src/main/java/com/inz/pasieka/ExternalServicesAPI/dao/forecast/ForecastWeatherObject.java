package com.inz.pasieka.ExternalServicesAPI.dao.forecast;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class ForecastWeatherObject {
    public int cod;
    public MainForecast main;
    public List<WeatherForecast> weather;
    public WindForecast wind;
    public String dt_txt;

}

//@Getter
//@Setter
//class MainForecast {
//    public double temp;
//    public double feels_like;
//    public double temp_min;
//    public double temp_max;
//    public int pressure;
//    public int humidity;
//}

//@Getter
//@Setter
//class WeatherForecast{
//    public int id;
//    public String main;
//    public String description;
//    public String icon;
//}
//
//@Getter
//@Setter
//class WindForeacst {
//    public double speed;
//    public int deg;
//}