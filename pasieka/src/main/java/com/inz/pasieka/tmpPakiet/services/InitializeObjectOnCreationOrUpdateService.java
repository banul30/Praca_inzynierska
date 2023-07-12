package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.Schedulers.UpdateWeightData;
import com.inz.pasieka.Weather.Services.WeatherAlertService;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTO;
import org.springframework.stereotype.Service;

@Service
public class InitializeObjectOnCreationOrUpdateService {
    private final WeatherAlertService weatherAlertService;
    private final UpdateWeightData updateWeightData;
    private final AlertPogodowyService alertPogodowyService;

    public InitializeObjectOnCreationOrUpdateService(WeatherAlertService weatherAlertService, UpdateWeightData updateWeightData, AlertPogodowyService alertPogodowyService) {
        this.weatherAlertService = weatherAlertService;
        this.updateWeightData = updateWeightData;
        this.alertPogodowyService = alertPogodowyService;
    }

    public void initializePasiekaOnCreation(Long pasiekaId, PasiekaDTO pasieka) {
        weatherAlertService.processWeatherDataByPasiekaId(pasiekaId, pasieka);
    }

    public void initializeDaneWagoweByWagaId(Long wagaId) {
    updateWeightData.updateScalesDataByWagaId(wagaId);
    }

    public void repopulateWeatherAlertsForPasieka (Long pasiekaId, PasiekaDTO pasiekaDTO) {

        alertPogodowyService.deleteAllWeatherAlertsByPasiekaId(pasiekaId);
        weatherAlertService.processWeatherDataByPasiekaId(pasiekaId, pasiekaDTO);

    }


}