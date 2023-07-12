package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.entities.Alert;
import com.inz.pasieka.tmpPakiet.services.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/alert")
public class AlertController {
private final AlertService alertService;


//todo wydaje mi się że ten controller jest po prostu do usunięcia

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

//    @GetMapping("/{ulId}")
//    public ResponseEntity<Object> getAlertsByUlId(@PathVariable("ulId") Long ulId) {
//        return alertService.getAllerts(ulId);
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Object> addNewAlert(@RequestBody Alert alert) {
//        alertService.addAlert(alert);
//        return new ResponseEntity<>("Alert created!", HttpStatus.CREATED);
//    }
//
//    @PutMapping(path = "/put/{alertId}")
//    public ResponseEntity<Object> updateAlert(@PathVariable("alertId") Long alertId, @RequestBody Alert alert){
//        alertService.updateAlert(alertId,alert);
//        return new ResponseEntity<>("Alert patched!",HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("/delete/{alertId}")
//    public ResponseEntity<Object> deleteAlert(@PathVariable("alertId") Long alertId){
//        return alertService.deleteAlert(alertId);
//    }

}
