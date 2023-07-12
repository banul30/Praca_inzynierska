package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.entities.Subskrypcja;
import com.inz.pasieka.tmpPakiet.services.SubskrypcjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/subskrypcja")
public class SubskrypcjaController {

    private final SubskrypcjaService subskrypcjaService;

    @Autowired
    public SubskrypcjaController(SubskrypcjaService subskrypcjaService){
        this.subskrypcjaService = subskrypcjaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Subskrypcja> getSubskrypcja(){
        return subskrypcjaService.getAllSubskrypcja();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addNewSubskrypcja(@RequestBody Subskrypcja subskrypcja){
        subskrypcjaService.addSubskrypcja(subskrypcja);
        return new ResponseEntity<>("Subskrypcja created!",HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{subskrypcjaId}")
    public ResponseEntity<Object> deleteSubskrypcjaById(@PathVariable("subskrypcjaId") Long subskrypcjaId){
        return  subskrypcjaService.deleteSubskrypcja(subskrypcjaId);
    }
}
