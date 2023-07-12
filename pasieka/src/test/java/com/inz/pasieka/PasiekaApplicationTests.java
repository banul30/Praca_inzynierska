package com.inz.pasieka;

import com.inz.pasieka.ExternalServicesAPI.Services.WeatherService;
import com.inz.pasieka.tmpPakiet.ServicesInterfaces.AlertServiceInterface;
import com.inz.pasieka.tmpPakiet.dto.MatkaPszczelaDTOS.MatkaPszczelaDTO;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTO;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTOWithId;
import com.inz.pasieka.tmpPakiet.entities.*;
import com.inz.pasieka.tmpPakiet.repositories.AlertPogodowyRepository;
import com.inz.pasieka.tmpPakiet.repositories.MatkaPszczelaRepository;
import com.inz.pasieka.tmpPakiet.repositories.PasiekaRepository;
import com.inz.pasieka.tmpPakiet.repositories.UlRepository;
import com.inz.pasieka.tmpPakiet.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class PasiekaApplicationTests {



    @Test
    public void should_convert_pasieka_to_pasiekaDto() {

        // given
        PasiekaRepository pasiekaRepository = new MockPasiekaRepository();
        AlertPogodowyRepository alertPogodowyRepository = new MockAlertPogodowyRepository();
        AlertPogodowyService alertPogodowyService = new AlertPogodowyService(alertPogodowyRepository);
        UlRepository ulRepository = new MockUlRepository();
        ModelMapper modelMapper = new ModelMapper();
        UlServiceInterface ulServiceInterface = new MockUlServiceInterface();
        WeatherService weatherService = new MockWeatherService();
         final PasiekaServiceInterfaceImpl pasiekaServiceImpl =
                 new PasiekaServiceInterfaceImpl(pasiekaRepository, alertPogodowyService, ulRepository, modelMapper, ulServiceInterface, weatherService);
        PasiekaDTO pasiekaDTO = new PasiekaDTO("name", 10.5, 20.5);

         // when
        Pasieka pasieka = pasiekaServiceImpl.convertToPasiekaFromPasiekaDto(pasiekaDTO);

        // then
        Assertions.assertEquals(pasieka.getNazwa(), pasiekaDTO.getNazwa());
        Assertions.assertEquals(pasieka.getLat(), pasiekaDTO.getLat());
        Assertions.assertEquals(pasieka.getLon(), pasiekaDTO.getLon());


    }

    @Test
    void should_convert_to_pasiekaDTOWIthId_from_pasieka() {
        //given
        PasiekaRepository pasiekaRepository = new MockPasiekaRepository();
        AlertPogodowyRepository alertPogodowyRepository = new MockAlertPogodowyRepository();
        AlertPogodowyService alertPogodowyService = new AlertPogodowyService(alertPogodowyRepository);
        UlRepository ulRepository = new MockUlRepository();
        ModelMapper modelMapper = new ModelMapper();
        UlServiceInterface ulServiceInterface = new MockUlServiceInterface();
        WeatherService weatherService = new MockWeatherService();
        final PasiekaServiceInterfaceImpl pasiekaServiceImpl =
                new PasiekaServiceInterfaceImpl(pasiekaRepository, alertPogodowyService, ulRepository, modelMapper, ulServiceInterface, weatherService);

        Pasieka pasieka = new Pasieka(1L,"mock1Pasieka",
                21.21,22.22,10L,"mockCity1","mocNote1",
                LocalDate.now(),null,null,null,null,null,null);

        //when
        PasiekaDTOWithId pasiekaDTOWithId = pasiekaServiceImpl.convertToPasiekaDTOWithIdFromPasieka(pasieka);
        //then
        Assertions.assertEquals(pasiekaDTOWithId.getNazwa(), pasieka.getNazwa());
        Assertions.assertEquals(pasiekaDTOWithId.getLat(), pasieka.getLat());
        Assertions.assertEquals(pasiekaDTOWithId.getLon(), pasieka.getLon());

    }


    @Test
    void should_return_lon_in_range_0_180() {
        //given
        PasiekaRepository pasiekaRepository = new MockPasiekaRepository();
        AlertPogodowyRepository alertPogodowyRepository = new MockAlertPogodowyRepository();
        AlertPogodowyService alertPogodowyService = new AlertPogodowyService(alertPogodowyRepository);
        UlRepository ulRepository = new MockUlRepository();
        ModelMapper modelMapper = new ModelMapper();
        UlServiceInterface ulServiceInterface = new MockUlServiceInterface();
        WeatherService weatherService = new MockWeatherService();
        final PasiekaServiceInterfaceImpl pasiekaServiceImpl =
                new PasiekaServiceInterfaceImpl(pasiekaRepository, alertPogodowyService, ulRepository, modelMapper, ulServiceInterface, weatherService);

        PasiekaDTO pasiekaDTO1 = new PasiekaDTO("name", 10.5, 2005.4);
        PasiekaDTO pasiekaDTO2 = new PasiekaDTO("name", 10.5, 2025.4);
        PasiekaDTO pasiekaDTO3 = new PasiekaDTO("name", 10.5, -2222.22);
        PasiekaDTO pasiekaDTO4 = new PasiekaDTO("name", 10.5, 12121.2121);
        PasiekaDTO pasiekaDTO5 = new PasiekaDTO("name", 10.5, 333.333);

        //when
        //then
        Assertions.assertTrue(pasiekaServiceImpl.fixParallelUniversesLongitudeProblem(pasiekaDTO1)<=180);
        Assertions.assertTrue(pasiekaServiceImpl.fixParallelUniversesLongitudeProblem(pasiekaDTO2)<=180);
        Assertions.assertTrue(pasiekaServiceImpl.fixParallelUniversesLongitudeProblem(pasiekaDTO3)<=180);
        Assertions.assertTrue(pasiekaServiceImpl.fixParallelUniversesLongitudeProblem(pasiekaDTO4)<=180);
        Assertions.assertTrue(pasiekaServiceImpl.fixParallelUniversesLongitudeProblem(pasiekaDTO5)<=180);


    }

    @Test
    void should_return_false_on_pasieka_bad_health() {
        //given
        PasiekaRepository pasiekaRepository = new MockPasiekaRepository();
        AlertPogodowyRepository alertPogodowyRepository = new MockAlertPogodowyRepository();
        AlertPogodowyService alertPogodowyService = new AlertPogodowyService(alertPogodowyRepository);
        UlRepository ulRepository = new MockUlRepository();
        ModelMapper modelMapper = new ModelMapper();
        UlServiceInterface ulServiceInterface = new MockUlServiceInterface();
        WeatherService weatherService = new MockWeatherService();
        final PasiekaServiceInterfaceImpl pasiekaServiceImpl =
                new PasiekaServiceInterfaceImpl(pasiekaRepository, alertPogodowyService, ulRepository, modelMapper, ulServiceInterface, weatherService);

        Set<Alert> alertSet = new HashSet<>();
        alertSet.add(new Alert());
        Set<Ul> badHealthUle = new HashSet<>();
        Ul badHealthUl = new Ul();
        badHealthUl.setAlertSet(alertSet);
        badHealthUle.add(badHealthUl);


        Pasieka badHealthPasieka = new Pasieka(1L,"mock1Pasieka",
                21.21,22.22,10L,"mockCity1","mocNote1",
                LocalDate.now(),badHealthUle,null,new AlertSpolecznosciowy(),null,null,null);

        //when
        boolean badHealth = pasiekaServiceImpl.checkHealthOfPasieka(badHealthPasieka);
        //then
        Assertions.assertFalse(badHealth);


    }

    @Test
    void should_return_true_on_pasieka_good_health() {
        //given
        PasiekaRepository pasiekaRepository = new MockPasiekaRepository();
        AlertPogodowyRepository alertPogodowyRepository = new MockAlertPogodowyRepository();
        AlertPogodowyService alertPogodowyService = new AlertPogodowyService(alertPogodowyRepository);
        UlRepository ulRepository = new MockUlRepository();
        ModelMapper modelMapper = new ModelMapper();
        UlServiceInterface ulServiceInterface = new MockUlServiceInterface();
        WeatherService weatherService = new MockWeatherService();
        final PasiekaServiceInterfaceImpl pasiekaServiceImpl =
                new PasiekaServiceInterfaceImpl(pasiekaRepository, alertPogodowyService, ulRepository, modelMapper, ulServiceInterface, weatherService);

        Set<Ul> goodHealthUle = new HashSet<>();
        Ul goodHealthUl = new Ul();
        goodHealthUl.setAlertSet(new HashSet<>());
        goodHealthUle.add(goodHealthUl);


        Pasieka goodHealthPasieka = new Pasieka(1L,"mock1Pasieka",
                21.21,22.22,10L,"mockCity1","mocNote1",
                LocalDate.now(),goodHealthUle,null,null,null,null,null);

        //when
        boolean goodHealth =  pasiekaServiceImpl.checkHealthOfPasieka(goodHealthPasieka);
        //then
        Assertions.assertTrue(goodHealth);

    }

    @Test
    void should_return_zero_alerts_for_pasieka() {
        //given
        PasiekaRepository pasiekaRepository = new MockPasiekaRepository();
        AlertPogodowyRepository alertPogodowyRepository = new MockAlertPogodowyRepository();
        AlertPogodowyService alertPogodowyService = new AlertPogodowyService(alertPogodowyRepository);
        UlRepository ulRepository = new MockUlRepository();
        ModelMapper modelMapper = new ModelMapper();
        UlServiceInterface ulServiceInterface = new MockUlServiceInterface();
        WeatherService weatherService = new MockWeatherService();
        final PasiekaServiceInterfaceImpl pasiekaServiceImpl =
                new PasiekaServiceInterfaceImpl(pasiekaRepository, alertPogodowyService, ulRepository, modelMapper, ulServiceInterface, weatherService);

        Set<Ul> zeroAlertsUlSet = new HashSet<>();
        Ul noAlertsUl = new Ul();
        noAlertsUl.setAlertSet(new HashSet<>());
        zeroAlertsUlSet.add(noAlertsUl);


        Pasieka zeroAlertsPasieka = new Pasieka(1L,"mock1Pasieka",
                21.21,22.22,10L,"mockCity1","mocNote1",
                LocalDate.now(),zeroAlertsUlSet,null,new AlertSpolecznosciowy(),null,null,null);

    //when
        int numberOfAlerts = pasiekaServiceImpl.getLiczbaAlertowForPasieka(zeroAlertsPasieka);

        //then
        Assertions.assertEquals(0, numberOfAlerts);


    }


    @Test
    void should_convert_to_matka_pszczela_from_matka_pszczela_dto_post() {

        //given
        MatkaPszczelaRepository matkaPszczelaRepository = new MockMatkaPszczelaRepository();
        ModelMapper modelMapper = new ModelMapper();
        UlServiceInterface ulServiceInterface = new MockUlServiceInterface();
        AlertServiceInterface alertService = new MockAlertService();

    final MatkaPszczelaService matkaPszczelaService = new MatkaPszczelaService(
                matkaPszczelaRepository,
                modelMapper,
            ulServiceInterface,
                alertService

        );
    MatkaPszczelaDTO matkaPszczelaDTO = new MatkaPszczelaDTO(LocalDate.now(),"na polu znaleziona");
    //when
        MatkaPszczela matkaPszczela = matkaPszczelaService.convertToMatkaPszczelaFromMatkaPszczelaDTOPost(matkaPszczelaDTO, 1L);
     //then
        Assertions.assertEquals(matkaPszczelaDTO.getDataWprowadzenia(),matkaPszczelaDTO.getDataWprowadzenia());
        Assertions.assertEquals(matkaPszczelaDTO.getRodzajPozyskania(),matkaPszczelaDTO.getRodzajPozyskania());




    }


}

