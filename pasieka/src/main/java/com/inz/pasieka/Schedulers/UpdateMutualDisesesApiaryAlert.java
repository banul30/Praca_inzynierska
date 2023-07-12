package com.inz.pasieka.Schedulers;

import com.inz.pasieka.Emails.EmailDetails;
import com.inz.pasieka.Emails.EmailService;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTOWithId;
import com.inz.pasieka.tmpPakiet.entities.WspolnaBazaChorob;
import com.inz.pasieka.tmpPakiet.services.AlertSpolecznosciowyService;
import com.inz.pasieka.tmpPakiet.services.PasiekaServiceInterfaceImpl;
import com.inz.pasieka.tmpPakiet.services.UzytkownikService;
import com.inz.pasieka.tmpPakiet.services.WspolnaBazaChorobService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UpdateMutualDisesesApiaryAlert {
    private final PasiekaServiceInterfaceImpl pasiekaServiceImpl;
    private final WspolnaBazaChorobService wspolnaBazaChorobService;
    private final AlertSpolecznosciowyService alertSpolecznosciowyService;
    private final EmailService emailService;
    private final UzytkownikService uzytkownikService;
    private final static int LICZBA_POBLISKICH_ZJAWISK=10;
    private final static int PROMIEN_SPRAWDZANEGO_OBSZARU=30;

    public UpdateMutualDisesesApiaryAlert(PasiekaServiceInterfaceImpl pasiekaServiceImpl, WspolnaBazaChorobService wspolnaBazaChorobService, AlertSpolecznosciowyService alertSpolecznosciowyService, EmailService emailService, UzytkownikService uzytkownikService) {
        this.pasiekaServiceImpl = pasiekaServiceImpl;
        this.wspolnaBazaChorobService = wspolnaBazaChorobService;
        this.alertSpolecznosciowyService = alertSpolecznosciowyService;
        this.emailService = emailService;
        this.uzytkownikService = uzytkownikService;
    }

//  @Scheduled(cron = "0 1 * * * *", zone = "Europe/Paris")

//    @Scheduled(cron = "21 37 * * * *", zone = "Europe/Paris") //pierwsza liczba to godzina druga to munutym.. 1h w przód od zimowego??
@Scheduled(cron = "23 14 * * * *", zone = "Europe/Paris") //pierwsza liczba to godzina druga to munutym.. 1h w przód od zimowego??
//todo zrobić to 1 raz w tughodniu żeby mu maila nie spamić
//@Scheduled(fixedDelay = 1000)

    @Transactional
  public void checkAlerts() {
      Set<PasiekaDTOWithId> p1 = pasiekaServiceImpl.getPasieki();
       List<PasiekaDTOWithId> listaPasiek = p1.stream().filter(x -> !x.isAlertSpolecznosciowyExistance()).collect(Collectors.toList());
        List<WspolnaBazaChorob> w1 = wspolnaBazaChorobService.getAllMutualChoroby();
        int pasiekaWrazliwosc;

        for (PasiekaDTOWithId pasiekaDTOWithId : listaPasiek) {
            pasiekaWrazliwosc = 0;
            for (WspolnaBazaChorob wspolnaBazaChorob : w1) {
                if (checkDistance(pasiekaDTOWithId.getLon(), pasiekaDTOWithId.getLat(), wspolnaBazaChorob.getLon(), wspolnaBazaChorob.getLat()) <= PROMIEN_SPRAWDZANEGO_OBSZARU) {
                    pasiekaWrazliwosc++;
                }
            }
            if (pasiekaWrazliwosc > LICZBA_POBLISKICH_ZJAWISK) {
                raiseAlert(pasiekaDTOWithId.getPasiekaId(), pasiekaDTOWithId.getNazwa());
            }
        }
    }

    //todo transactional??
    public void raiseAlert(Long pasiekaId, String nazwa) {
        alertSpolecznosciowyService.addAlertSpolecznosciowyToPasieka(pasiekaId);
        sendEmailNotyficationToOwnerOfPasiekaById(pasiekaId, nazwa);
    }

    public double checkDistance(double lon1, double lat1, double lon2, double lat2) {  //from https://dzone.com
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return dist;
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public void sendEmailNotyficationToOwnerOfPasiekaById(Long pasiekaId, String nazwa) {
        //todo naprawić spam alertów
        String mail = uzytkownikService.getMailOfUserByPasiekaId(pasiekaId);
    emailService.sendSimpleMail(new EmailDetails(
            mail,
            "Uwaga,użytkownicy zgłaszają wystąpienia  zgnilca amerykańskiego w pobliżu twojej pasieki o nazwie "+nazwa,
            "[ALERT PASIECZNY]"));
    }

}
