package com.inz.pasieka.tmpPakiet.services;

import com.inz.pasieka.ExternalServicesAPI.Helpers.WeatherDataForWidgetCombined;
import com.inz.pasieka.ExternalServicesAPI.Services.WeatherService;
import com.inz.pasieka.tmpPakiet.Exception.ExistanceValidationException;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTO;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTONote;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTOWithId;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.repositories.PasiekaRepository;
import com.inz.pasieka.tmpPakiet.repositories.UlRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PasiekaServiceInterfaceImpl  {

    private final PasiekaRepository pasiekaRepository;
    private final AlertPogodowyService alertPogodowyService;
    private final UlRepository ulRepository;
    private final ModelMapper modelMapper;
    private final UlServiceInterface ulServiceInterface;
    private final WeatherService weatherService;


    public PasiekaServiceInterfaceImpl(PasiekaRepository pasiekaRepository,
                                       AlertPogodowyService alertPogodowyService,
                                       UlRepository ulRepository,
                                       ModelMapper modelMapper,
                                       UlServiceInterface ulServiceInterface,
                                       WeatherService weatherService) {
        this.pasiekaRepository = pasiekaRepository;
        this.alertPogodowyService = alertPogodowyService;
        this.ulRepository = ulRepository;
        this.modelMapper = modelMapper;
        this.ulServiceInterface = ulServiceInterface;
        this.weatherService = weatherService;
    }

    public Set<PasiekaDTOWithId> getPasieki() {
        Set<Pasieka> wholePasiekaSet = pasiekaRepository.findAllPasieki();
        Set<PasiekaDTOWithId> dtosy = new HashSet<>();
        wholePasiekaSet.forEach(v->dtosy.add(
                this.convertToPasiekaDTOWithIdFromPasieka(v)
                        .setHealthyChain(this.checkHealthOfPasieka(v))
                        .setAtmosphericStatusChain(this.atmosphericStatusOfPasieka(v))
                        .setLiczbaUli(v)
                        .setLiczbaAlertow(this.getLiczbaAlertowForPasieka(v))
                        .setAlertSpolecznosciowyExistance(v)
                        .setCityNameChain(v.getCityName())
               )
        );

        return dtosy;
    }

    public List<PasiekaDTOWithId> getPasiekiForUser(String username) {
        List<Pasieka> wholePasiekaSet = pasiekaRepository.findAllPasiekiForUser(username);
        List<PasiekaDTOWithId> dtosy = new ArrayList<>();
        wholePasiekaSet.forEach(v->dtosy.add(
                this.convertToPasiekaDTOWithIdFromPasieka(v)
                        .setHealthyChain(this.checkHealthOfPasieka(v))
                        .setAtmosphericStatusChain(this.atmosphericStatusOfPasieka(v))
                        .setLiczbaUli(v)
                        .setLiczbaAlertow(this.getLiczbaAlertowForPasieka(v))
                        .setAlertSpolecznosciowyExistance(v)
                        .setCityNameChain(v.getCityName())
                )
        );

        return dtosy;
    }

    public Set<Pasieka> getWholePasieki() {
        return pasiekaRepository.findAllPasieki();
    }

    public Pasieka getPasiekaById(Long id) {
        return pasiekaRepository.getById(id);
    }

    public Pasieka convertToPasiekaFromPasiekaDto(PasiekaDTO pasiekaDTO) {
        Pasieka p1;
        p1 = modelMapper.map(pasiekaDTO, Pasieka.class);
        p1.setLon(fixParallelUniversesLongitudeProblem(pasiekaDTO));
        return p1;
    }

    public Pasieka populatePasiekaWithWeatherData (Pasieka p1) {
        WeatherDataForWidgetCombined weatherDataForWidgetCombined = weatherService.getWeatherData(p1.getLat().toString(),p1.getLon().toString());
        p1.setCityName(weatherDataForWidgetCombined.getCityName());
        p1.setCityId(weatherDataForWidgetCombined.getCityId());
        return p1;
    }

    public PasiekaDTOWithId convertToPasiekaDTOWithIdFromPasieka(Pasieka pasieka) {
        PasiekaDTOWithId p1;
        p1 = modelMapper.map(pasieka, PasiekaDTOWithId.class);
        return p1;
    }

   public double fixParallelUniversesLongitudeProblem(PasiekaDTO pasiekaDTO) {
        double lon = pasiekaDTO.getLon();
        if (lon<-180) {
            int tmp = (int) (lon/180);

            return lon+180*Math.abs(tmp);
        } else if (lon>180) {
            int tmp = (int) (lon/180);
            return lon-180*Math.abs(tmp);
        }
        return lon;
    }



    public Long addPasieka(Pasieka p1) {
       Pasieka savedPasieka = pasiekaRepository.save(p1);
       Long pasiekaId = savedPasieka.getPasiekaId();
       return pasiekaId;
    }


    @Transactional
    public void deletePasieka(Long id) {
        if(!pasiekaRepository.existsById(id)) {
           throw new ExistanceValidationException("Taka pasieka nie istnieje!");
        }

       if (this.CheckIfAnyUlAssociatedWithPasieka(id)) {
            deleteUleFromPasieka(id);
       }

       pasiekaRepository.deleteAssociationsWithAlertPogodowy(id);
       pasiekaRepository.deleteAssociationsWithAlertSpolecznosciowy(id);
       pasiekaRepository.deleteAssociationsWithPokarm(id);
       pasiekaRepository.deleteById(id);
    }

    public void deleteUleFromPasieka (Long id) {
        if(!pasiekaRepository.existsById(id)) {
            throw new ExistanceValidationException("Taka pasieka nie istnieje!");
        }

        Pasieka p1 = pasiekaRepository.getById(id);
        p1.getUle().forEach(x-> ulServiceInterface.deleteUl(x.getUlId()));

    }

    private boolean CheckIfAnyUlAssociatedWithPasieka(Long id) {
        return ulRepository.countNumberOfUlInPasieka(id) != 0;
    }

    @Transactional
    public ResponseEntity<Object> updatePasieka(Long pasiekaId, PasiekaDTO pasiekaDTO) {
        if(!pasiekaRepository.existsById(pasiekaId)) {
            throw new ExistanceValidationException("Taka pasieka nie istnieje!");
        }

        pasiekaDTO.setLon(fixParallelUniversesLongitudeProblem(pasiekaDTO));

        WeatherDataForWidgetCombined weatherDataForWidgetCombined = weatherService.getWeatherData(pasiekaDTO.getLat().toString(),pasiekaDTO.getLon().toString());

        int res = pasiekaRepository.updatePasiekaById(
                pasiekaDTO.getNazwa(),
                pasiekaDTO.getLat(),
                pasiekaDTO.getLon(),
                weatherDataForWidgetCombined.getCityId(),
                weatherDataForWidgetCombined.getCityName(),
                pasiekaId);


        if(res == 0){
            return new ResponseEntity<>("Couldn't update pasieka with id " + pasiekaId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Ul with id " + pasiekaId + " updated successfully", HttpStatus.OK);

    }

    public boolean checkHealthOfPasieka(Pasieka p1) { //todo

        if (p1.getAlertSpolecznosciowy()!=null) {
            return false;
        }
        List<Ul> ule = new ArrayList<>(p1.getUle());
        for (Ul ul : ule) {
            if (ul.getAlertSet().size() > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean atmosphericStatusOfPasieka(Pasieka p1) {
        return alertPogodowyService.checkAlertPogodowyByPasiekaId(p1.getPasiekaId());

    }

    public int getLiczbaAlertowForPasieka(Pasieka p1) {
        return p1.getUle().stream().mapToInt(x -> x.getAlertSet().size()).sum();
    }

    public int checkIfPasiekaExists(Long pasiekaId) {
       return pasiekaRepository.checkIfPasiekaExists(pasiekaId);
    }


    public void updateNotatkaData(PasiekaDTONote pasiekaDTONote) {
        pasiekaRepository.updatePasiekaNoteData(pasiekaDTONote.getDate(),pasiekaDTONote.getNote(),pasiekaDTONote.getPasiekaId());
    }

    public void deleteNotatka(Long pasiekaId) {
        pasiekaRepository.purgePasiekaNoteData(pasiekaId);
    }



}
