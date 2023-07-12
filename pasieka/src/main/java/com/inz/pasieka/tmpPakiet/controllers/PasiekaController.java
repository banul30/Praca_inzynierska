package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTO;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTONote;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTOWithId;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.entities.Uzytkownik;
import com.inz.pasieka.tmpPakiet.security.services.UzytkownikAplikacjiService;
import com.inz.pasieka.tmpPakiet.services.InitializeObjectOnCreationOrUpdateService;
import com.inz.pasieka.tmpPakiet.services.PasiekaServiceInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/pasieka")
public class PasiekaController {

    private final PasiekaServiceInterfaceImpl pasiekaServiceImpl;
    private final UzytkownikAplikacjiService uzytkownikAplikacjiService;
    private final InitializeObjectOnCreationOrUpdateService initializeObjectOnCreationOrUpdateService;

    @Autowired
    public PasiekaController(PasiekaServiceInterfaceImpl pasiekaServiceImpl, UzytkownikAplikacjiService uzytkownikAplikacjiService, InitializeObjectOnCreationOrUpdateService initializeObjectOnCreationOrUpdateService){
        this.pasiekaServiceImpl = pasiekaServiceImpl;
        this.uzytkownikAplikacjiService = uzytkownikAplikacjiService;
        this.initializeObjectOnCreationOrUpdateService = initializeObjectOnCreationOrUpdateService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PasiekaDTOWithId> getPasieki() {
       String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return pasiekaServiceImpl.getPasieki();
    }

    @GetMapping(path="/get")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PasiekaDTOWithId> getPasiekiForUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return pasiekaServiceImpl.getPasiekiForUser(username);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:read')) and @userCheckUtils.checkPasiekaPermissions(#idPasieka, authentication.principal)")
    @GetMapping(path="/{idPasieka}")
    public Pasieka getPasiekaById(@PathVariable("idPasieka") Long idPasieka) {
        return pasiekaServiceImpl.getPasiekaById(idPasieka);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('app:write')")
    @PostMapping(path="/add")
    public ResponseEntity<Object> addNewPasieka(@RequestBody PasiekaDTO pasiekaDTO) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Uzytkownik uzytkownik = uzytkownikAplikacjiService.getUzytkownikAplikacji(username).getUzytkownik();
        Pasieka pasieka = pasiekaServiceImpl.convertToPasiekaFromPasiekaDto(pasiekaDTO);
        pasieka = pasiekaServiceImpl.populatePasiekaWithWeatherData(pasieka);

        pasieka.setUzytkownik(uzytkownik);
        Long pasiekaId = pasiekaServiceImpl.addPasieka(pasieka);
        initializeObjectOnCreationOrUpdateService.initializePasiekaOnCreation(pasiekaId, pasiekaDTO);
        return new ResponseEntity<>("Pasieka created!",HttpStatus.CREATED);
    }

    @PostMapping(path="/add/notatka")
    public ResponseEntity<Object> addNoteToPasieka(@RequestBody PasiekaDTONote pasiekaDTONote) {
        pasiekaServiceImpl.updateNotatkaData(pasiekaDTONote);
        return new ResponseEntity<>("Note created!",HttpStatus.CREATED);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkPasiekaPermissions(#idPasieka, authentication.principal)")
    @DeleteMapping(path="/delete/notatka/{pasiekaId}")
    public ResponseEntity<Object> addNoteToPasieka(@PathVariable("pasiekaId") Long idPasieka) {
        pasiekaServiceImpl.deleteNotatka(idPasieka);
        return new ResponseEntity<>("Note deleted!",HttpStatus.CREATED);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkPasiekaPermissions(#idPasieka, authentication.principal)")
    @DeleteMapping(path="/delete/{idPasieka}")
    public ResponseEntity<Object> deletePasieka(@PathVariable("idPasieka") Long idPasieka) {
        pasiekaServiceImpl.deletePasieka(idPasieka);
        return new ResponseEntity<>("Pasieka deleted!",HttpStatus.OK);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:update')) and @userCheckUtils.checkPasiekaPermissions(#pasiekaId, authentication.principal)")
    @PutMapping(path = "/update/{pasiekaId}")
    public ResponseEntity<Object> patchPasieka(@PathVariable("pasiekaId") Long pasiekaId,@RequestBody PasiekaDTO pasiekaDTO) {
        pasiekaServiceImpl.updatePasieka(pasiekaId,pasiekaDTO);
        initializeObjectOnCreationOrUpdateService.repopulateWeatherAlertsForPasieka(pasiekaId, pasiekaDTO);
        return new ResponseEntity<>("Pasieka updated!",HttpStatus.OK);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkPasiekaPermissions(#idPasieka, authentication.principal)")
    @DeleteMapping(path="/delete/allUle/{idPasieka}")
    public ResponseEntity<Object> deleteUleFromPasieka(@PathVariable("idPasieka") Long idPasieka) {

        pasiekaServiceImpl.deleteUleFromPasieka(idPasieka);
        return new ResponseEntity<>("Ule deleted!",HttpStatus.OK);
    }



    
}
