package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.Exception.ExistanceValidationException;
import com.inz.pasieka.tmpPakiet.ServicesInterfaces.AlertServiceInterface;
import com.inz.pasieka.tmpPakiet.ServicesInterfaces.MatkaPszczelaInterface;
import com.inz.pasieka.tmpPakiet.dto.MatkaPszczelaDTOS.MatkaPszczelaDTO;
import com.inz.pasieka.tmpPakiet.entities.MatkaPszczela;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.repositories.MatkaPszczelaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatkaPszczelaService implements MatkaPszczelaInterface {
    private final static Long OLD_MATKA_PSZCZELA_ALERT_ID=1L;
    private final static Long NO_MATKA_PSZCZELA_ALERT_ID=2L;

    private final MatkaPszczelaRepository matkaPszczelaRepository;
    private final ModelMapper modelMapper;
    private final UlServiceInterface ulServiceInterface;
    private final AlertServiceInterface alertService;


    public MatkaPszczelaService(MatkaPszczelaRepository matkaPszczelaRepository, ModelMapper modelMapper, UlServiceInterface ulServiceInterface, AlertServiceInterface alertService) {
        this.matkaPszczelaRepository = matkaPszczelaRepository;
        this.modelMapper = modelMapper;
        this.ulServiceInterface = ulServiceInterface;
        this.alertService = alertService;
    }

    public List<MatkaPszczela> getAllMatki(){
        return matkaPszczelaRepository.findAll();
    }


    @Transactional

    public void addMatka(MatkaPszczela matkaPszczela, Long ulId){
        MatkaPszczela MatkaZapisana = matkaPszczelaRepository.save(matkaPszczela);
        matkaPszczelaRepository.updateRelationWithUl(MatkaZapisana.getMatkaPszczelaId(), matkaPszczela.getUl().getUlId()); //todo obrzydliwe to jest

        alertService.deleteAlertFormUl(NO_MATKA_PSZCZELA_ALERT_ID, ulId);


    };

    @Transactional
    public ResponseEntity<Object> updateMatka(MatkaPszczelaDTO matkaPszczela, Long matkaId){
        if(!matkaPszczelaRepository.existsById(matkaId)){
            return new ResponseEntity<>("Matka pszczela with id "+matkaId+" doesn't exist", HttpStatus.NOT_FOUND);
        }
        int res = matkaPszczelaRepository.updateMatkaPszczelaById(matkaPszczela.getDataWprowadzenia(), matkaPszczela.getRodzajPozyskania(), matkaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't update matka pszczela with id "+ matkaId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Matka pszczela with id "+matkaId+" updated successfully", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> deleteMatka(Long matkaId, Long idUl) {
        if(!matkaPszczelaRepository.existsById(matkaId)){
            return new ResponseEntity<>("Matka pszczela with id "+matkaId+" doesn't exist", HttpStatus.NOT_FOUND);
        }
        matkaPszczelaRepository.removeMatkaPszcelaIdFromAssociatedUlByMatkaId(matkaId);
        int res = matkaPszczelaRepository.deleteAllByMatkaPszczelaId(matkaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't delete matka pszczela with id "+ matkaId, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        alertService.linkAlertWithUl(NO_MATKA_PSZCZELA_ALERT_ID,idUl);
        return new ResponseEntity<>("Matka pszczela with id "+matkaId+" deleted successfully", HttpStatus.OK);
    }

    public MatkaPszczela convertToMatkaPszczelaFromMatkaPszczelaDTOPost(MatkaPszczelaDTO matkaPszczelaDTO, Long ulId){
        MatkaPszczela m1;
        m1 = modelMapper.map(matkaPszczelaDTO, MatkaPszczela.class);
       Ul ul1 = ulServiceInterface.getUlById(ulId);
       if (ul1 ==null) {
           throw new ExistanceValidationException("taki ul nie istnieje, nie można dodać matki!");
       }
        m1.setUl(ul1);
        return m1;
    }

}
