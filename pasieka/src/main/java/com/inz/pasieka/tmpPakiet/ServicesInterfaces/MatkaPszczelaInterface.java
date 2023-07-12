package com.inz.pasieka.tmpPakiet.ServicesInterfaces;

import com.inz.pasieka.tmpPakiet.dto.MatkaPszczelaDTOS.MatkaPszczelaDTO;
import com.inz.pasieka.tmpPakiet.entities.MatkaPszczela;

public interface MatkaPszczelaInterface {
     MatkaPszczela convertToMatkaPszczelaFromMatkaPszczelaDTOPost(MatkaPszczelaDTO matkaPszczelaDTO, Long ulId);

    }
