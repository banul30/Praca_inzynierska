package com.inz.pasieka.Schedulers;

import com.inz.pasieka.tmpPakiet.entities.AlertSpolecznosciowy;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.services.AlertSpolecznosciowyService;
import com.inz.pasieka.tmpPakiet.services.PasiekaServiceInterfaceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeleteOldMutualDiseasesFromApiary {

    private final PasiekaServiceInterfaceImpl pasiekaServiceImpl;
    private final AlertSpolecznosciowyService alertSpolecznosciowyService;

    public DeleteOldMutualDiseasesFromApiary(PasiekaServiceInterfaceImpl pasiekaServiceImpl, AlertSpolecznosciowyService alertSpolecznosciowyService) {
        this.pasiekaServiceImpl = pasiekaServiceImpl;
        this.alertSpolecznosciowyService = alertSpolecznosciowyService;
    }
    @Scheduled(cron = "21 39 * * * *", zone = "Europe/Paris") //todo inny cron wymyślić
//    @Scheduled(fixedDelay = 1000)

    public void cleanApiaryAlertData() {
        List<Pasieka> listaPasiek = pasiekaServiceImpl.getWholePasieki().stream().filter(x -> x.getAlertSpolecznosciowy() != null).distinct().collect(Collectors.toList());
        for (int i=0;i< listaPasiek.size();i++) {
            AlertSpolecznosciowy alertSpolecznosciowy = listaPasiek.get(i).getAlertSpolecznosciowy();
                if (isAfterBasedOnDate(alertSpolecznosciowy.getDate().toInstant(),Instant.now().minus(360, ChronoUnit.DAYS))) {
                    alertSpolecznosciowyService.deleteAlertSpolecznosciowyFromPasieka(alertSpolecznosciowy.getAlertSpolecznosciowyId());
                }
        }
    }

    public boolean isAfterBasedOnDate(Instant instant, Instant compareTo) {
        return instant.truncatedTo(ChronoUnit.DAYS)
                .isAfter(compareTo.truncatedTo(ChronoUnit.DAYS));
    }
}
