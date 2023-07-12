package com.inz.pasieka.tmpPakiet.controllers;


import com.inz.pasieka.tmpPakiet.dto.PokarmDTOS.PokarmDTO;
import com.inz.pasieka.tmpPakiet.entities.Pokarm;
import com.inz.pasieka.tmpPakiet.services.PokarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/pokarm")
public class PokarmController {
    private final PokarmService pokarmService;

    public PokarmController(PokarmService pokarmService) {
        this.pokarmService = pokarmService;
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addNewPokarm(@RequestBody PokarmDTO pokarmDTO) {
        pokarmService.addPokarm(pokarmService.convertToPokarmFromPokarmDto(pokarmDTO));
        return new ResponseEntity<>("Pokarm created!",HttpStatus.CREATED);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:update')) and @userCheckUtils.checkPokarmPermissions(#pokarmId, authentication.principal)")
    @PutMapping(path = "/put/{pokarmId}")
    public ResponseEntity<Object> updatePokarm(@PathVariable("pokarmId") Long pokarmId, @RequestBody Pokarm pokarm){
         return pokarmService.updatePokarm(pokarmId,pokarm);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkPokarmPermissions(#pokarmId, authentication.principal)")
    @DeleteMapping("/delete/{pokarmId}")
    public ResponseEntity<Object> deletePokarm(@PathVariable("pokarmId") Long pokarmId){
        return pokarmService.deletePokarm(pokarmId);
    }


}
