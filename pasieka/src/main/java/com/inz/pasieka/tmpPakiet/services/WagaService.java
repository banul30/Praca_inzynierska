package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.Exception.ExistanceValidationException;
import com.inz.pasieka.tmpPakiet.dto.WagaDTOS.WagaDTO;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.entities.Waga;
import com.inz.pasieka.tmpPakiet.repositories.WagaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WagaService {

    private final WagaRepository wagaRepository;
    private final ModelMapper modelMapper;
    private final UlServiceInterfaceImpl ulService;

    public WagaService(WagaRepository wagaRepository, ModelMapper modelMapper, UlServiceInterfaceImpl ulService) {
        this.wagaRepository = wagaRepository;
        this.modelMapper = modelMapper;
        this.ulService = ulService;
    }

    @Autowired

    public List<Waga> getAllWagi(){ //todo to powinien być set
        List<Waga> all = wagaRepository.findAll();
        return all;
    }

    @Transactional
    public Waga addWaga(Waga waga){
        Waga w1 = wagaRepository.save(waga);
        wagaRepository.updateRelationWithUl(w1.getWagaId(), waga.getUl().getUlId());
        return w1;

    }

    @Transactional
    public ResponseEntity<Object> putWaga(Long wagaId, WagaDTO waga){
        if(!wagaRepository.existsById(wagaId)){
            return new ResponseEntity<>("Waga with id "+wagaId+" doesn't exist", HttpStatus.NOT_FOUND);
        }

        int res = wagaRepository.updateWagaByWagaId(waga.getProducent(),waga.getModel(),wagaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't update waga with id "+ wagaId,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Waga with id "+wagaId+" updated successfully", HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<Object> deleteWaga(Long wagaId) {

        if(!wagaRepository.existsById(wagaId)){
            return new ResponseEntity<>("Waga with id "+wagaId+" doesn't exist", HttpStatus.NOT_FOUND);
        }

        wagaRepository.removeWagaIdFromAssociatedUlByWagaId(wagaId);
        wagaRepository.removeAssociaatedDataFromScalestData(wagaId);
        int res = wagaRepository.deleteWagaById(wagaId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't delete waga with id "+ wagaId,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Waga with id "+wagaId+" deleted successfully", HttpStatus.OK);

    }

    public Waga convertWagaDtoPostToWaga(WagaDTO wagaDTOPost, Long ulId) {


        Waga w1 = new Waga();
//        w1 = modelMapper.map(wagaDTOPost, Waga.class);
        w1.setProducent(wagaDTOPost.getProducent());
        w1.setModel(wagaDTOPost.getModel());
        w1.setApiID(wagaDTOPost.getApiID());
        Ul ul1 = ulService.getUlById(ulId);
        if (ul1 ==null) {
            throw new ExistanceValidationException("taki ul nie istnieje, nie można dodać wagi!");
        }
        w1.setUl(ul1);
        return w1;

    }

}
