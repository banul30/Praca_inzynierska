package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.ServicesInterfaces.AlertPogodowyInterface;
import com.inz.pasieka.tmpPakiet.entities.AlertPogodowy;
import com.inz.pasieka.tmpPakiet.repositories.AlertPogodowyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertPogodowyService implements AlertPogodowyInterface {

    private final AlertPogodowyRepository alertPogodowyRepository;

    @Autowired
    public AlertPogodowyService( AlertPogodowyRepository alertPogodowyRepository) {
        this.alertPogodowyRepository = alertPogodowyRepository;
    }


    public void addAlertPogoddowy(AlertPogodowy alertPogodowy){
        alertPogodowyRepository.save(alertPogodowy);
    }

    public boolean checkAlertPogodowyByPasiekaId (Long pasiekaId) {
        int exists = alertPogodowyRepository.checkAlertExistanceByPasiekaId(pasiekaId);
        return exists!=0;
    }

    public void deleteAllWeatherAlerts(){
        alertPogodowyRepository.deleteAll();
    }

    public void deleteAllWeatherAlertsByPasiekaId(Long pasiekaId) {
        alertPogodowyRepository.deleteByPasiekaId(pasiekaId);
    }

}
