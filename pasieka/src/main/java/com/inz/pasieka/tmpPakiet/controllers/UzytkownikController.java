package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.dto.UzytkownikDTOS.UzytkownikDTO;
import com.inz.pasieka.tmpPakiet.entities.Uzytkownik;
import com.inz.pasieka.tmpPakiet.security.entities.UzytkownikAplikacji;
import com.inz.pasieka.tmpPakiet.services.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/uzytkownik")
public class UzytkownikController {

    private final UzytkownikService uzytkownikService;

    @Autowired
    public UzytkownikController(UzytkownikService uzytkownikService){
        this.uzytkownikService = uzytkownikService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Uzytkownik getUzytkownik(){
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return uzytkownikService.getAllUzytkownik(username);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addNewUzytkownik(@RequestBody Uzytkownik uzytkownik){
        uzytkownikService.addUzytkownik(uzytkownik);
        return new ResponseEntity<>("Uzytkownik created!",HttpStatus.CREATED);
    }

    @PutMapping(path = "/put")
    public ResponseEntity<Object> updateUzytkownik(@RequestBody UzytkownikDTO uzytkownikDTO){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return uzytkownikService.putUzytkownik(username, uzytkownikDTO);
    }

    @DeleteMapping(path = "/delete/{uzytkownikId}")
    public ResponseEntity<Object> deleteUzytkownikById(@PathVariable("uzytkownikId") Long uzytkownikId){
        return uzytkownikService.deleteUzytkownik(uzytkownikId);
    }


}
