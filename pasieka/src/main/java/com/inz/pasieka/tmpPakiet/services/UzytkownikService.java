package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.dto.UzytkownikDTOS.UzytkownikDTO;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.entities.Uzytkownik;
import com.inz.pasieka.tmpPakiet.repositories.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UzytkownikService {

    private final UzytkownikRepository uzytkownikRepository;
    private final PasiekaServiceInterfaceImpl pasiekaServiceImpl;

    @Autowired
    public UzytkownikService(UzytkownikRepository uzytkownikRepository, PasiekaServiceInterfaceImpl pasiekaServiceImpl){
        this.uzytkownikRepository = uzytkownikRepository;
        this.pasiekaServiceImpl = pasiekaServiceImpl;
    }

    public Uzytkownik getAllUzytkownik(String username){
        return uzytkownikRepository.findUzytkownikByUsername(username);
    }

    public void addUzytkownik(Uzytkownik uzytkownik){
        uzytkownikRepository.save(uzytkownik);
    }

    @Transactional
    public ResponseEntity<Object> putUzytkownik(String username, UzytkownikDTO uzytkownik){

        int res = uzytkownikRepository.updateUzytkownikByUsername(uzytkownik.getImie(), uzytkownik.getNazwisko(),username);
        if(res == 0){
            return new ResponseEntity<Object>("Couldn't update uzytkownik " + username,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Uzytkownik  " + username + " updated successfully", HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<Object> deleteUzytkownik(Long uzytkownikId) {

        if(!uzytkownikRepository.existsById(uzytkownikId)){
            return new ResponseEntity<>("Uzytkownik with id " + uzytkownikId + " doesn't exist", HttpStatus.NOT_FOUND);
        }
//        int res = uzytkownikRepository.deleteAllUzytkownikById(uzytkownikId);

        Uzytkownik u1 = uzytkownikRepository.getById(uzytkownikId);
        Set<Pasieka> pasiekiUzytkownika = u1.getPasiekaSet();

        if (pasiekiUzytkownika!=null) {
            for (Pasieka pasieka : pasiekiUzytkownika) {
                pasiekaServiceImpl.deletePasieka(pasieka.getPasiekaId());
            }
        }
        uzytkownikRepository.deleteById(uzytkownikId);

        return new ResponseEntity<>("Uzytkownik with id " + uzytkownikId + " deleted successfully", HttpStatus.OK);

    }

    public String getMailOfUserByPasiekaId(Long pasiekaID) {
        return uzytkownikRepository.getEmailByPasiekaId(pasiekaID);
    }

}
