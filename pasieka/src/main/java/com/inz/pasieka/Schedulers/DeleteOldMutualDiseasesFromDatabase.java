package com.inz.pasieka.Schedulers;

import com.inz.pasieka.tmpPakiet.services.WspolnaBazaChorobService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class DeleteOldMutualDiseasesFromDatabase {
    private final WspolnaBazaChorobService wspolnaBazaChorobService;

    public DeleteOldMutualDiseasesFromDatabase(WspolnaBazaChorobService wspolnaBazaChorobService) {
        this.wspolnaBazaChorobService = wspolnaBazaChorobService;
    }

   @Scheduled(cron = "21 38 * * * *", zone = "Europe/Paris") //bodaj godzinka w tył od naszego zimowego

    public void manageDieseses() {
        Instant i1 = Instant.now().minus(60, ChronoUnit.DAYS);
        wspolnaBazaChorobService.deleteOldData(i1);
    }

}
