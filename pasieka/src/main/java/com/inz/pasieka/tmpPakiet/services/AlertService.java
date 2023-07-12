package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.ServicesInterfaces.AlertServiceInterface;
import com.inz.pasieka.tmpPakiet.repositories.AlertRepository;
import org.springframework.stereotype.Service;

@Service
public class AlertService implements AlertServiceInterface {
    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void linkAlertWithUl(Long alertId, Long ulId) {

        alertRepository.linkAlertWithUl(alertId, ulId);
    }

    public void deleteAlertFormUl(Long alertId, Long ulId) {
        alertRepository.deleteAlertFormUl(alertId, ulId);

    }
}
