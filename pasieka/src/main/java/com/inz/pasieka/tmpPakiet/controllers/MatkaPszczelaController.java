package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.dto.MatkaPszczelaDTOS.MatkaPszczelaDTO;
import com.inz.pasieka.tmpPakiet.entities.MatkaPszczela;
import com.inz.pasieka.tmpPakiet.services.MatkaPszczelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "api/tests/matka")
public class MatkaPszczelaController {

    private final MatkaPszczelaService matkaPszczelaService;

    @Autowired
    public  MatkaPszczelaController(MatkaPszczelaService matkaPszczelaService){
        this.matkaPszczelaService = matkaPszczelaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<MatkaPszczela> getMatki(){
        return matkaPszczelaService.getAllMatki();
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:write')) and @userCheckUtils.checkUlPermissions(#ulId, authentication.principal)")
    @PostMapping("/add/{ulId}")
    public ResponseEntity<Object> addNewMatka(@Valid @RequestBody MatkaPszczelaDTO matkaPszczelaDTO, @PathVariable("ulId") Long ulId){
        matkaPszczelaService.addMatka(matkaPszczelaService.convertToMatkaPszczelaFromMatkaPszczelaDTOPost(matkaPszczelaDTO, ulId), ulId);
        return new ResponseEntity<>("Matka pszczela created!", HttpStatus.CREATED);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:update')) and @userCheckUtils.checkMatkaPszczelaPermissions(#idMatka, authentication.principal)")
    @PutMapping("/put/{idMatka}")
    public ResponseEntity<Object> updateMatka(@RequestBody MatkaPszczelaDTO matkaPszczelaDTO, @PathVariable  Long idMatka){
        return matkaPszczelaService.updateMatka(matkaPszczelaDTO,idMatka);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkMatkaPszczelaPermissions(#idMatka, authentication.principal)")
    @DeleteMapping("/delete/{idMatka}/{idUl}")
    public ResponseEntity<Object> deleteMatka(@PathVariable Long idMatka, @PathVariable Long idUl){
        return matkaPszczelaService.deleteMatka(idMatka, idUl);
    }

}
