package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.Exception.ExistanceValidationException;
import com.inz.pasieka.tmpPakiet.dto.UlDTOS.UlDTO;
import com.inz.pasieka.tmpPakiet.dto.UlDTOS.UlDTOPost;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.repositories.PasiekaRepository;
import com.inz.pasieka.tmpPakiet.repositories.UlRepository;
import com.inz.pasieka.tmpPakiet.repositories.WagaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UlServiceInterfaceImpl implements UlServiceInterface {
    private final static Long OLD_MATKA_PSZCZELA_ALERT_ID=1L;
    private final static Long NO_MATKA_PSZCZELA_ALERT_ID=2L;

    private final UlRepository ulRepository;
    private final WagaRepository wagaRepository;
    private final PasiekaRepository pasiekaRepository;
    private final ModelMapper modelMapper;
    private final AlertService alertService;


    @Autowired
    public UlServiceInterfaceImpl(UlRepository ulRepository, WagaRepository wagaRepository, PasiekaRepository pasiekaRepository, ModelMapper modelMapper, AlertService alertService) {
        this.ulRepository = ulRepository;
        this.wagaRepository = wagaRepository;
        this.pasiekaRepository = pasiekaRepository;
        this.modelMapper = modelMapper;
        this.alertService = alertService;
    }

    public List<Ul> getAllUle(){
        List<Ul> all = ulRepository.findAllUle();
        return all;
    }

    public Set<Ul> getUleOfPasieka(Long pasiekaId) {
        return ulRepository.findByPasiekaId(pasiekaId);
    }

    @Transactional
    public void addUl(Ul ul) {
        ulRepository.save(ul);
        //todo alert brak matki
        alertService.linkAlertWithUl(NO_MATKA_PSZCZELA_ALERT_ID,ul.getUlId());
    }

    public Ul getUlById(Long ulId) {
        if(!ulRepository.existsById(ulId)) {
            throw new ExistanceValidationException("Taki ul nie istnieje!");
        }
     return ulRepository.findById(ulId).get();
    }

    @Transactional
    public void deleteUl(Long ulId) {

       if(!ulRepository.existsById(ulId)) {
            throw new ExistanceValidationException("Taki ul nie istnieje!");
        }

       ulRepository.deleteAssociationsWithChoroba(ulId);
       ulRepository.deleteAssociationsWithAlert(ulId);
       ulRepository.deleteById(ulId);

    }


//    public void joinUlWithPasieka(Ul ul){
//        if (!pasiekaRepository.existsById(ul.getPasieka().getPasiekaId())) {
//            throw new ExistanceValidationException("Nie można dodać od pasieki, taka pasieka nie istnieje");
//        }
//        ulRepository.joinUlWithPasieka(ul.getUlId(),ul.getPasieka().getPasiekaId());
//    }




    @Transactional
    public ResponseEntity<Object> updateUl(Long ulId, UlDTO ulDTO) {

        if(!ulRepository.existsById(ulId)) {
            throw new ExistanceValidationException("Taki ul nie istnieje!");
        }

        int res = ulRepository.updateUlById(ulDTO.getNazwa(), ulDTO.getPoziomAgresji(), ulDTO.getRodzajKorpusu(), ulDTO.getRodzajRamek(), ulDTO.getRasa(), ulId);

        if(res == 0){
            return new ResponseEntity<>("Couldn't update ul with id " + ulId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Ul with id " + ulId + " updated successfully", HttpStatus.OK);

    }


    @Transactional
    public void addChorobaToUl(Long chorobaId, Long ulId) {
       if (ulRepository.findByChorobaId(chorobaId,ulId)==0)
        ulRepository.addChorobaToUl(chorobaId,ulId);
    }

    @Transactional
    public void deleteChorobaById(Long chorobaId, Long ulId) {
        ulRepository.deleteChorobaById(chorobaId,ulId);
    }

    public Ul convertToUlFromUlDtoPost(UlDTOPost ulDTOpost) {
        Ul u1;
        u1 = modelMapper.map(ulDTOpost, Ul.class);
        return u1;
    }


}
