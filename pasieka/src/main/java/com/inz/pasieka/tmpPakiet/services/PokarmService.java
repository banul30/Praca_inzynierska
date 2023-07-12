package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.tmpPakiet.Exception.ExistanceValidationException;
import com.inz.pasieka.tmpPakiet.dto.PokarmDTOS.PokarmDTO;
import com.inz.pasieka.tmpPakiet.entities.Pokarm;
import com.inz.pasieka.tmpPakiet.repositories.PokarmRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PokarmService {

    private final PokarmRepository pokarmRepository;
    private final ModelMapper modelMapper;
    private final PasiekaServiceInterfaceImpl pasiekaServiceImpl;

    public PokarmService(PokarmRepository pokarmRepository, ModelMapper modelMapper, PasiekaServiceInterfaceImpl pasiekaServiceImpl) {
        this.pokarmRepository = pokarmRepository;
        this.modelMapper = modelMapper;
        this.pasiekaServiceImpl = pasiekaServiceImpl;
    }

    public Pokarm convertToPokarmFromPokarmDto(PokarmDTO pokarmDTO) {
        if (pasiekaServiceImpl.checkIfPasiekaExists(pokarmDTO.getPasiekaId())==0) {
            throw new ExistanceValidationException("nie ma takiej pasieki! nie można dodać pokarmu");
        }
        Pokarm pokarm;
        pokarm = modelMapper.map(pokarmDTO, Pokarm.class);
        pokarm.setPokarmId(null);
        pokarm.setPasiekaPokarm(pasiekaServiceImpl.getPasiekaById(pokarmDTO.getPasiekaId()) );
        return pokarm;
    }

    public void addPokarm(Pokarm pokarm) {
        pokarmRepository.save(pokarm);
    }

    public ResponseEntity<Object> updatePokarm(Long pokarmId, Pokarm pokarm) {

        if(!pokarmRepository.existsById(pokarmId)){
            return new ResponseEntity<>("Pokarm with id "+pokarmId+" doesn't exist", HttpStatus.NOT_FOUND);
        }

        int res = pokarmRepository.updatePokarmByPokarmId(pokarm.getMasa(), pokarm.getRodzaj(), pokarmId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't update pokarm with id " + pokarmId, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Pokarm with id " + pokarmId + " updated successfully", HttpStatus.OK);
    }



    public ResponseEntity<Object> deletePokarm(Long pokarmId) {
        if(!pokarmRepository.existsById(pokarmId)){
            return new ResponseEntity<>("Pokarm with id "+pokarmId+" doesn't exist", HttpStatus.NOT_FOUND);
        }
//        pokarmRepository.deleteAssociationsWithPasieka(pokarmId);
        int res = pokarmRepository.deletePokarmByPokarmId(pokarmId);
        if(res == 0){
            return new ResponseEntity<>("Couldn't delete pokarm with id "+ pokarmId,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Pokarm with id "+pokarmId+" deleted successfully", HttpStatus.OK);
    }
}
