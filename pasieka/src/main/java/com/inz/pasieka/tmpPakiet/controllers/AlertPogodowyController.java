package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.entities.AlertPogodowy;
import com.inz.pasieka.tmpPakiet.services.AlertPogodowyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/alertPogodowy")
public class AlertPogodowyController {

    private final AlertPogodowyService alertPogodowyService;

    @Autowired
    public  AlertPogodowyController(AlertPogodowyService alertPogodowyService){
        this.alertPogodowyService = alertPogodowyService;
    }

    //prawdopodobnie nastąpi całkowite przebdowanie logiki alertów, nie będzie to api udostępnione przez http, będziemy to załatwiać wewnętrznie
    //poprzez pakiet Schedulers
//
//    @GetMapping("/{localization}")
//    @ResponseStatus(HttpStatus.OK)
//    public Iterable<AlertPogodowy> getAllAlertPogodowyforLocalization(@PathVariable("localization") String localization){
//        return alertPogodowyService.getAllAlertPogodowyforLocalization(localization);
//    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Iterable<AlertPogodowy> getAllAlertPogodowy(){
//        return alertPogodowyService.getAllAlertPogodowy();
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Object> addNewAlertPogodowy(@RequestBody AlertPogodowy alertPogodowy){
//        alertPogodowyService.addAlertPogoddowy(alertPogodowy);
//        return new ResponseEntity<>("Alert pogodowy created!",HttpStatus.CREATED);
//    }

//    @PutMapping(path = "/put/{alertId}")
//    public ResponseEntity<Object> updateAlertPogodowy(@PathVariable("alertId") Long alertId, @RequestBody AlertPogodowy alertPogodowy){
//        return alertPogodowyService.putAlertPogodowy(alertId,alertPogodowy);
//    }
//
//    @DeleteMapping("/delete/{alertId}")
//    public ResponseEntity<Object> deleteAlertPogodowy(@PathVariable("alertId") Long alertId){
//        return alertPogodowyService.deleteAlertPogodowy(alertId);
//    }

}
