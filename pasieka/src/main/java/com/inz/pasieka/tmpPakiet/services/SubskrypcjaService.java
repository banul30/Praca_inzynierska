package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.entities.Subskrypcja;
import com.inz.pasieka.tmpPakiet.repositories.SubskrypcjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubskrypcjaService {

    private final SubskrypcjaRepository subskrypcjaRepository;

    @Autowired
    public  SubskrypcjaService(SubskrypcjaRepository subskrypcjaRepository){
        this.subskrypcjaRepository = subskrypcjaRepository;
    }

    public List<Subskrypcja> getAllSubskrypcja(){
        List<Subskrypcja> all = subskrypcjaRepository.findAll();
        return all;
    }

    public void addSubskrypcja(Subskrypcja subskrypcja){
        subskrypcjaRepository.save(subskrypcja);
    }



    @Transactional
    public ResponseEntity<Object> deleteSubskrypcja(Long subskrypcjaId) {

        if(!subskrypcjaRepository.existsById(subskrypcjaId)){
            return new ResponseEntity<>("Subskrypcja with id " + subskrypcjaId + " doesn't exist", HttpStatus.NOT_FOUND);
        }
        int res = subskrypcjaRepository.deleteAllSubskrypcjaById(subskrypcjaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't delete subskrypcja with id " + subskrypcjaId,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Subskrypcja with id " + subskrypcjaId + " deleted successfully", HttpStatus.OK);

    }


}
