package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.entities.AlertSpolecznosciowy;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.repositories.AlertSpolecznosciowyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AlertSpolecznosciowyService {
    SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
String  dateObj = LocalDate.now().minus(90, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    Date date =formatter1.parse(dateObj);

    String typ = "Uwaga zgnilec amerykański w okolicy";
    private final AlertSpolecznosciowyRepository alertSpolecznosciowyRepository;
    private final PasiekaServiceInterfaceImpl pasiekaServiceImpl;


    public AlertSpolecznosciowyService(AlertSpolecznosciowyRepository alertSpolecznosciowyRepository, PasiekaServiceInterfaceImpl pasiekaServiceImpl) throws ParseException {
        this.alertSpolecznosciowyRepository = alertSpolecznosciowyRepository;
        this.pasiekaServiceImpl = pasiekaServiceImpl;
    }

    @Transactional
    public void addAlertSpolecznosciowyToPasieka(Long pasiekaId){
        Pasieka p1 = pasiekaServiceImpl.getPasiekaById(pasiekaId);
        AlertSpolecznosciowy as1 = new AlertSpolecznosciowy(date,typ,p1);
        alertSpolecznosciowyRepository.save(as1);

    }
    @Transactional
    public void deleteAlertSpolecznosciowyFromPasieka(Long alertId){
    alertSpolecznosciowyRepository.deleteByPasiekaId(alertId);
    }
}
