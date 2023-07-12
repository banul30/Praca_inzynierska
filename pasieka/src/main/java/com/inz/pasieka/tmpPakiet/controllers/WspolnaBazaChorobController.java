package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.dto.BazaChorobDTOS.ChorobaRecordDTO;

import com.inz.pasieka.tmpPakiet.services.WspolnaBazaChorobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/tests/bazaChorob")
public class WspolnaBazaChorobController {
    private final WspolnaBazaChorobService wspolnaBazaChorobService;

    @Autowired
    public WspolnaBazaChorobController(WspolnaBazaChorobService wspolnaBazaChorobService) {
        this.wspolnaBazaChorobService = wspolnaBazaChorobService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addNewChoroba(@RequestBody ChorobaRecordDTO chorobaWspolna) { //todo tutaj dto
        wspolnaBazaChorobService.addChorobaToDatabase(chorobaWspolna);
        return new ResponseEntity<>("Choroba added to databse!", HttpStatus.CREATED);
    }
}
