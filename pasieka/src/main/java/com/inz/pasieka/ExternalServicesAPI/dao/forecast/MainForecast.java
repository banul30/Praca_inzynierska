package com.inz.pasieka.ExternalServicesAPI.dao.forecast;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainForecast {
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;
}
