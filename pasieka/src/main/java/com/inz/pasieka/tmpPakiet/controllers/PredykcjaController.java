package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.entities.Predykcja;
import com.inz.pasieka.tmpPakiet.services.PredykcjaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/predykcja")
public class PredykcjaController {

    private final PredykcjaService predykcjaService;

    public PredykcjaController(PredykcjaService predykcjaService){
        this.predykcjaService = predykcjaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Predykcja> getPredykcja(){
        return predykcjaService.getAllPredykcja();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addNewPredykcja(@RequestBody Predykcja predykcja){
        predykcjaService.addPredykcja(predykcja);
        return new ResponseEntity<>("Predykcja created!",HttpStatus.CREATED);
    }

    @PutMapping(path = "/put/{predykcjaId}")
    public ResponseEntity<Object> updatePredykcja(@PathVariable("predykcjaId") Long predykcjaId,@RequestBody Predykcja predykcja){
        return predykcjaService.putPredykcja(predykcjaId, predykcja);
    }

    @DeleteMapping(path = "/delete/{predykcjaId}")
    public ResponseEntity<Object> deletePredykcjaById(@PathVariable("predykcjaId") Long predykcjaId){
        return  predykcjaService.deletePredykcja(predykcjaId);
    }

}
