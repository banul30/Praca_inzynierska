package com.inz.pasieka.Schedulers;

import com.inz.pasieka.ExternalServicesAPI.Scales.ScalesServiceBeeHub;
import com.inz.pasieka.ExternalServicesAPI.Scales.ScalesWeightData;
import com.inz.pasieka.tmpPakiet.entities.DaneWagowe;
import com.inz.pasieka.tmpPakiet.entities.Waga;
import com.inz.pasieka.tmpPakiet.repositories.DaneWagoweRepository;
import com.inz.pasieka.tmpPakiet.services.WagaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateWeightData {
    private final ScalesServiceBeeHub scalesServiceBeeHub;
    private final DaneWagoweRepository daneWagoweRepository;
    private final WagaService wagaService;

    public UpdateWeightData(ScalesServiceBeeHub scalesServiceBeeHub, DaneWagoweRepository daneWagoweRepository, WagaService wagaService) {
        this.scalesServiceBeeHub = scalesServiceBeeHub;
        this.daneWagoweRepository = daneWagoweRepository;
        this.wagaService = wagaService;
    }

 @Scheduled(cron = "17 21 * * * *", zone = "Europe/Paris") //bodaj godzinka w tył od naszego zimowego
//@Scheduled(fixedDelay = 200)
    public void updateScalesData() {
        List<Waga> wagi = wagaService.getAllWagi();
    for (Waga waga : wagi) {

        ScalesWeightData scalesWeightData = scalesServiceBeeHub.getData(waga.getApiID());
        DaneWagowe sd1=new DaneWagowe(scalesWeightData.getMinWeight(), scalesWeightData.getMaxWeight(), scalesWeightData.getDateForInternalUse(), waga);
        daneWagoweRepository.save(sd1);

    }
    }

    public void updateScalesDataByWagaId (Long wagaId) {
        Waga waga = wagaService.getAllWagi().stream().filter(x-> x.getWagaId().equals(wagaId)).findAny().get();
        List<ScalesWeightData> scalesWeightDataList = scalesServiceBeeHub.getDataLast7Days(waga.getApiID());

        for (ScalesWeightData scalesWeightData:scalesWeightDataList) {
            DaneWagowe sd1=new DaneWagowe(scalesWeightData.getMinWeight(), scalesWeightData.getMaxWeight(), scalesWeightData.getDateForInternalUse(), waga);
            daneWagoweRepository.save(sd1);
        }

    }
}
