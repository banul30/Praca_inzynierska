package com.inz.pasieka.tmpPakiet.ServicesInterfaces;

import com.inz.pasieka.tmpPakiet.dto.MatkaPszczelaDTOS.MatkaPszczelaDTO;
import com.inz.pasieka.tmpPakiet.entities.MatkaPszczela;

public interface AlertServiceInterface {

     void deleteAlertFormUl(Long alertId, Long ulId);
     void linkAlertWithUl(Long alertId, Long ulId);


    }
