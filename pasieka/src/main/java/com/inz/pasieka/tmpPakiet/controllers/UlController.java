package com.inz.pasieka.tmpPakiet.controllers;

import com.inz.pasieka.tmpPakiet.dto.UlDTOS.UlDTO;
import com.inz.pasieka.tmpPakiet.dto.UlDTOS.UlDTOPost;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.services.UlServiceInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/tests/ul")
public class UlController {

    private final UlServiceInterfaceImpl ulService;

    @Autowired
    public UlController(UlServiceInterfaceImpl ulService){
        this.ulService = ulService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ul> getUle(){
        return ulService.getAllUle();
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:read')) and @userCheckUtils.checkPasiekaPermissions(#idPasieka, authentication.principal)")
    @GetMapping(path="/pasieka/{idPasieka}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Ul> getUleOfPasiekaById(@PathVariable("idPasieka") Long idPasieka) {
        return ulService.getUleOfPasieka(idPasieka);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:read')) and @userCheckUtils.checkUlPermissions(#ulId, authentication.principal)")
    @GetMapping(path="/{ulId}")
    @ResponseStatus(HttpStatus.OK)
    public Ul getUlById(@PathVariable("ulId") Long ulId) {
        return ulService.getUlById(ulId);
    }

    @PostMapping(path="/add")
    public ResponseEntity<Object> addNewUl(@RequestBody UlDTOPost ulDTOPost) {
        ulService.addUl(ulService.convertToUlFromUlDtoPost(ulDTOPost));
        return new ResponseEntity<>("Ul created!",HttpStatus.CREATED);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkUlPermissions(#ulId, authentication.principal)")
    @DeleteMapping(path = "/delete/{ulId}")
    public ResponseEntity<Object> deleteUlById(@PathVariable("ulId") Long ulId) {
        ulService.deleteUl(ulId);
        return new ResponseEntity<>("Ul deleted!",HttpStatus.OK);
    }



    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:update')) and @userCheckUtils.checkUlPermissions(#ulId, authentication.principal)")
    @PutMapping(path = "/update/{ulId}")
    public ResponseEntity<Object> patchUl(@PathVariable("ulId") Long ulId,@RequestBody UlDTO ulDTO) {
        ulService.updateUl(ulId,ulDTO);

            return new ResponseEntity<>("Ul updated!",HttpStatus.OK);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:write')) and @userCheckUtils.checkUlPermissions(#ulId, authentication.principal)")
    @PostMapping(path="/add/{chorobaId}/{ulId}")
    public ResponseEntity<Object> addChorobaToUl(@PathVariable("chorobaId") Long chorobaId, @PathVariable("ulId") Long ulId) {
        ulService.addChorobaToUl(chorobaId, ulId);
        return new ResponseEntity<>("Choroba added to ul!!",HttpStatus.CREATED);
    }

    @PreAuthorize(" (hasAuthority('ROLE_ADMIN') or hasAuthority('app:delete')) and @userCheckUtils.checkUlPermissions(#ulId, authentication.principal)")
    @DeleteMapping(path = "/delete/choroba/{chorobaId}/{ulId}")
    public ResponseEntity<Object> deleteChorobaById(@PathVariable("chorobaId") Long chorobaId, @PathVariable("ulId") Long ulId) {
        ulService.deleteChorobaById(chorobaId, ulId);
        return new ResponseEntity<>("Choroba deleted from ul!",HttpStatus.OK);
    }


}
