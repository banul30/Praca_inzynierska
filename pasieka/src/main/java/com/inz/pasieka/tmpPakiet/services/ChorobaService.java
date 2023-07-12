package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.entities.Choroba;
import com.inz.pasieka.tmpPakiet.repositories.ChorobaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChorobaService {
    private final ChorobaRepository chorobaRepository;

    public ChorobaService(ChorobaRepository chorobaRepository) {
        this.chorobaRepository = chorobaRepository;
    }

    public void addChoroba(Choroba choroba) {
        chorobaRepository.save(choroba);
    }
    public ResponseEntity<Object> updateChoroba(Long chorobaId, Choroba choroba) {
        if(!chorobaRepository.existsById(chorobaId)){
            return new ResponseEntity<>("Choroba with id "+chorobaId+" doesn't exist", HttpStatus.NOT_FOUND);
        }

        int res = chorobaRepository.updateChorobaById(choroba.getNazwa(), choroba.getOpis(), choroba.getAktywna(),chorobaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't update choroba with id " + chorobaId, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Choroba with id " + chorobaId + " updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteChoroba(Long chorobaId) {
        if(!chorobaRepository.existsById(chorobaId)){
            return new ResponseEntity<>("Choroba with id "+chorobaId+" doesn't exist", HttpStatus.NOT_FOUND);
        }
        chorobaRepository.deleteAssociationsWithUl(chorobaId);
        int res = chorobaRepository.deleteChorobaById(chorobaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't delete Choroba with id "+ chorobaId,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Choroba with id "+chorobaId+" deleted successfully", HttpStatus.OK);
    }
    public Iterable<Choroba> getAllChoroba() {

        return chorobaRepository.findAll();
    }


}
