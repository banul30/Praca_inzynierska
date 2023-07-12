package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.dto.WagaDTOS.WagaDTO;
import com.inz.pasieka.tmpPakiet.entities.Waga;
import com.inz.pasieka.tmpPakiet.enums.ProducentWag;
import com.inz.pasieka.tmpPakiet.services.InitializeObjectOnCreationOrUpdateService;
import com.inz.pasieka.tmpPakiet.services.WagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/tests/waga")
public class WagaController {

    private final WagaService wagaService;
    private final InitializeObjectOnCreationOrUpdateService initializeObjectOnCreationOrUpdateService;

    @Autowired
    public WagaController(WagaService wagaService, InitializeObjectOnCreationOrUpdateService initializeObjectOnCreationOrUpdateService){
        this.wagaService = wagaService;
        this.initializeObjectOnCreationOrUpdateService = initializeObjectOnCreationOrUpdateService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Waga> getWagi(){
        return wagaService.getAllWagi(); 
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:write')) and @userCheckUtils.checkUlPermissions(#ulId, authentication.principal)")
    @PostMapping(path = "/add/{ulId}")
    public ResponseEntity<Object> addNewWaga(@RequestBody WagaDTO wagaDTOPost, @PathVariable("ulId") Long ulId) {
        Waga w1 = wagaService.addWaga(wagaService.convertWagaDtoPostToWaga(wagaDTOPost,ulId));
        initializeObjectOnCreationOrUpdateService.initializeDaneWagoweByWagaId(w1.getWagaId());
        return new ResponseEntity<>("Waga created!",HttpStatus.CREATED);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:update')) and @userCheckUtils.checkWagaPermissions(#wagaId, authentication.principal)")
    @PutMapping(path = "/put/{wagaId}")
    public ResponseEntity<Object> updateWaga(@PathVariable("wagaId") Long wagaId,@RequestBody WagaDTO waga){
        return wagaService.putWaga(wagaId,waga);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkWagaPermissions(#wagaId, authentication.principal)")
    @DeleteMapping(path = "/delete/{wagaId}")
    public ResponseEntity<Object> deleteWagaById(@PathVariable("wagaId") Long wagaId){
        return  wagaService.deleteWaga(wagaId);
    }

    @GetMapping(path="/get/producent")
    public List<String> getAllWagaProducent() {
      List <String> producenci=new ArrayList<>();

        for (ProducentWag producentWag: ProducentWag.values()) {
            producenci.add(producentWag.name());
        }
      return producenci;

    }

}
