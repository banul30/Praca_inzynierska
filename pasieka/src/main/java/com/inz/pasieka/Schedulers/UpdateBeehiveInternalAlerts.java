package com.inz.pasieka.Schedulers;

import com.inz.pasieka.tmpPakiet.entities.MatkaPszczela;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.services.AlertService;
import com.inz.pasieka.tmpPakiet.services.UlServiceInterfaceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class UpdateBeehiveInternalAlerts {


    private final UlServiceInterfaceImpl ulService;
    private final AlertService alertService;
    private final static Long OLD_MATKA_PSZCZELA_ALERT_ID=1L;
    private final static Long NO_MATKA_PSZCZELA_ALERT_ID=2L;

    public UpdateBeehiveInternalAlerts(UlServiceInterfaceImpl ulService, AlertService alertService) {
        this.ulService = ulService;
        this.alertService = alertService;
    }

//    @Scheduled(fixedDelay = 1000)
@Scheduled(cron = "15 13 * * * *", zone = "Europe/Paris") //bodaj godzinka w tył od naszego zimowego

public void processInternalSystemData() {

       List<Ul> allUle= ulService.getAllUle();
        for (Ul ul : allUle) {
            if (ul.getMatkaPszczela() == null) {
                raiseNoMatkaPszczelaAlert(ul.getUlId());
            } else if (checkIfMatkaIsToOld(ul.getMatkaPszczela())) {
                raiseOldMatkaPszczelaAlert(ul.getUlId());
            }
        }
    }

    public void raiseOldMatkaPszczelaAlert(Long ulId) {
        alertService.linkAlertWithUl(OLD_MATKA_PSZCZELA_ALERT_ID,ulId);
    }

    public void raiseNoMatkaPszczelaAlert(Long ulId) {
        alertService.linkAlertWithUl(NO_MATKA_PSZCZELA_ALERT_ID,ulId);
    }

    public boolean checkIfMatkaIsToOld (MatkaPszczela matkaPszczela) {
        LocalDate dataWprowadzenia = matkaPszczela.getDataWprowadzenia();
        return dataWprowadzenia.isAfter(dataWprowadzenia.plus(2,ChronoUnit.YEARS));
    }


}
