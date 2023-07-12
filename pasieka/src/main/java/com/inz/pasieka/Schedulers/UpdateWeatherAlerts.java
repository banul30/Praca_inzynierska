package com.inz.pasieka.Schedulers;
import com.inz.pasieka.Weather.Services.WeatherAlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UpdateWeatherAlerts {

    private final WeatherAlertService weatherAlertService;

    public UpdateWeatherAlerts(WeatherAlertService weatherAlertService) {
        this.weatherAlertService = weatherAlertService;
    }

 //   @Scheduled(cron = "0 1 * * * *", zone = "Europe/Paris")
   @Scheduled(cron = "20 15 * * * *", zone = "Europe/Paris") //bodaj godzinka w tył od naszego zimowego

    public void scheduleAlertCheckCron() {
    weatherAlertService.processWeatherData();
    }

}
