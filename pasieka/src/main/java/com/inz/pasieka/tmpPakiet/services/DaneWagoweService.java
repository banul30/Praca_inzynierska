package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.entities.DaneWagowe;
import com.inz.pasieka.tmpPakiet.repositories.DaneWagoweRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaneWagoweService {

    private final DaneWagoweRepository daneWagoweRepository;

    public DaneWagoweService(DaneWagoweRepository daneWagoweRepository) {
        this.daneWagoweRepository = daneWagoweRepository;
    }

    public List<DaneWagowe> getDaneWagoweByWagaId(Long wagaId) {
       return daneWagoweRepository.getByWagaID(wagaId);
    }

}
