package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.dto.BazaChorobDTOS.ChorobaRecordDTO;
import com.inz.pasieka.tmpPakiet.entities.WspolnaBazaChorob;
import com.inz.pasieka.tmpPakiet.repositories.WspolnaBazaChorobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service

public class WspolnaBazaChorobService {
    private final WspolnaBazaChorobRepository wspolnaBazaChorobRepository;

    @Autowired
    public WspolnaBazaChorobService(WspolnaBazaChorobRepository wspolnaBazaChorobRepository) {
        this.wspolnaBazaChorobRepository = wspolnaBazaChorobRepository;
    }

    public void addChorobaToDatabase(ChorobaRecordDTO recordDto) {
        WspolnaBazaChorob wbch = new WspolnaBazaChorob();
        wbch.setLat(recordDto.getLat());
        wbch.setLon(recordDto.getLon());
        wbch.setDataWprowadzenia(LocalDate.now());
        wspolnaBazaChorobRepository.save(wbch);
    }

    public void deleteOldData(Instant i1) {
        wspolnaBazaChorobRepository.deleteByDate(i1);
    }

    public List<WspolnaBazaChorob> getAllMutualChoroby() {
        return wspolnaBazaChorobRepository.findAll();
    }

}
