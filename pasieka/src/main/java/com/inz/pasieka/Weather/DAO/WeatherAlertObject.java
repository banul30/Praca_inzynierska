package com.inz.pasieka.Weather.DAO;

import com.inz.pasieka.Enums.WeatherAlertsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherAlertObject {
    String dt_txt_time;
    WeatherAlertsEnum type;
    Long pasiekaId;

    public WeatherAlertObject(String dt_txt_time, WeatherAlertsEnum type, Long pasiekaId) {
        this.dt_txt_time = dt_txt_time.substring(0,dt_txt_time.indexOf(" "));
        this.type = type;
        this.pasiekaId = pasiekaId;
    }
}
