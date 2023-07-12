package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.entities.Predykcja;
import com.inz.pasieka.tmpPakiet.repositories.PredykcjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PredykcjaService {

    private final PredykcjaRepository predykcjaRepository;

    @Autowired
    public PredykcjaService(PredykcjaRepository predykcjaRepository){
        this.predykcjaRepository = predykcjaRepository;
    }

    public List<Predykcja> getAllPredykcja(){
        return predykcjaRepository.findAll();
    }

    public void addPredykcja(Predykcja predykcja){
        Predykcja p = predykcjaRepository.save(predykcja);
    }

    @Transactional
    public ResponseEntity<Object> putPredykcja(Long predykcjaId, Predykcja predykcja){
        if(!predykcjaRepository.existsById(predykcjaId)){
            return new ResponseEntity<>("Predykcja with id " + predykcjaId + " doesn't exist", HttpStatus.NOT_FOUND);
        }

        int res = predykcjaRepository.updatePredykcjaById(predykcja.getWspolczynnik(), predykcjaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't update predykcja with id " + predykcjaId, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Predykcja with id " + predykcjaId + " updated successfully", HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<Object> deletePredykcja(Long predykcjaId) {

        if(!predykcjaRepository.existsById(predykcjaId)){
            return new ResponseEntity<>("Predykcja with id " + predykcjaId + " doesn't exist", HttpStatus.NOT_FOUND);
        }
        int res = predykcjaRepository.deleteAllByPredykcjaId(predykcjaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't delete predykcja with id " + predykcjaId,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Predykcja with id " + predykcjaId + " deleted successfully", HttpStatus.OK);

    }
}
