package com.inz.pasieka.tmpPakiet.ServicesInterfaces;

import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTO;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;

public interface PasiekaServiceInterface {
     Pasieka convertToPasiekaFromPasiekaDto(PasiekaDTO pasiekaDTO);
}
