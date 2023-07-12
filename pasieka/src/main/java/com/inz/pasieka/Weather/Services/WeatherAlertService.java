package com.inz.pasieka.Weather.Services;

import com.inz.pasieka.Enums.WeatherAlertsEnum;
import com.inz.pasieka.Weather.DAO.WeatherAlertObject;
import com.inz.pasieka.ExternalServicesAPI.dao.forecast.ForecastWeatherObject;
import com.inz.pasieka.ExternalServicesAPI.dao.forecast.WeatherFiveDayForecastForPasieka;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTO;
import com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS.PasiekaDTOWithId;
import com.inz.pasieka.tmpPakiet.entities.AlertPogodowy;
import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.services.AlertPogodowyService;
import com.inz.pasieka.tmpPakiet.services.PasiekaServiceInterfaceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class WeatherAlertService {

    private static final int HUMIDITY_MIN_LEVEL=10;
    private static final int HUMIDITY_MAX_LEVEL=80;
    private static final int WIND_MAX_SPEED=60;
    private static final int MAX_TEMPERATURE_KELVIN =307;
    public static final List<Integer> extremeConditions = List.of(202, 212, 503, 504, 762, 781); //w dokumentacji openweather
    private final PasiekaServiceInterfaceImpl pasiekaServiceImpl;
    private final AlertPogodowyService alertPogodowyService;
    private final RestTemplate restTemplate;

    public WeatherAlertService(PasiekaServiceInterfaceImpl pasiekaServiceImpl, AlertPogodowyService alertPogodowyService, RestTemplate restTemplate) {
        this.pasiekaServiceImpl = pasiekaServiceImpl;
        this.alertPogodowyService = alertPogodowyService;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void processWeatherData() {
        Iterable<PasiekaDTOWithId> pasiekaCollection = pasiekaServiceImpl.getPasieki();
        List<WeatherAlertObject> alerts = new ArrayList<>();
        for(PasiekaDTOWithId p : pasiekaCollection) {
            Double lat = p.getLat();
            Double lon = p.getLon();
            Long pasiekaId = p.getPasiekaId();
            WeatherFiveDayForecastForPasieka fiveDayForecast = restTemplate.getForObject(
                    "https://api.openweathermap.org/data/2.5/forecast?lat=" +
                            lat.toString() +
                            "&" +
                            "lon=" +
                            lon.toString() +
                            "&" +
                            "appid=" +
                            "SECRET!",
                    WeatherFiveDayForecastForPasieka.class);

            ArrayList<ForecastWeatherObject> forecastsList;
            if (fiveDayForecast!=null) {
                 forecastsList = new ArrayList<>(fiveDayForecast.getList());
            } else {
                forecastsList=new ArrayList<>();
            }
            populateWithAlerts(alerts, pasiekaId, forecastsList);
        }

        if (alerts.size()!=0) {  //usuwa stare alerty żeby zapopulować nowymi
            this.deleteAllWeatherData();
        }

        Map<WeatherAlertsEnum,List<WeatherAlertObject>> groupByType = alerts.stream().collect(Collectors.groupingBy(WeatherAlertObject::getType));
        for (WeatherAlertsEnum al : WeatherAlertsEnum.values()) {
            if (groupByType.containsKey(al)) {
                writeAlertsToDB(groupByType, al);
            }
        }
    }

    public void processWeatherDataByPasiekaId (Long pasiekaId, PasiekaDTO p) {
        List<WeatherAlertObject> alerts = new ArrayList<>();

        Double lat = p.getLat();
        Double lon = p.getLon();
        WeatherFiveDayForecastForPasieka fiveDayForecast = restTemplate.getForObject(
                "https://api.openweathermap.org/data/2.5/forecast?lat=" +
                        lat.toString() +
                        "&" +
                        "lon=" +
                        lon.toString() +
                        "&" +
                        "appid=" +
                        "80d8fc8e3e61510393bb2f026594812e",  //todo przenieść to w bezpieczne miejsce
                WeatherFiveDayForecastForPasieka.class);

        ArrayList<ForecastWeatherObject> forecastsList;
        if (fiveDayForecast!=null) {
            forecastsList = new ArrayList<>(fiveDayForecast.getList());
        } else {
            forecastsList=new ArrayList<>();
        }
        populateWithAlerts(alerts, pasiekaId, forecastsList);

        Map<WeatherAlertsEnum,List<WeatherAlertObject>> groupByType = alerts.stream().collect(Collectors.groupingBy(WeatherAlertObject::getType));
        for (WeatherAlertsEnum al : WeatherAlertsEnum.values()) {
            if (groupByType.containsKey(al)) {
                writeAlertsToDB(groupByType, al);
            }
        }
    }

    private void populateWithAlerts(List<WeatherAlertObject> alerts, Long pasiekaId, ArrayList<ForecastWeatherObject> forecastsList) {
        for (ForecastWeatherObject forecastWeatherObject : forecastsList) {
            if (humidityLevelCheck(forecastWeatherObject)) {
                alerts.add(new WeatherAlertObject(forecastWeatherObject.dt_txt, WeatherAlertsEnum.HUMIDITY, pasiekaId));
            }
            if (windLevelCheck(forecastWeatherObject)) {
                alerts.add(new WeatherAlertObject(forecastWeatherObject.dt_txt, WeatherAlertsEnum.WIND, pasiekaId));
            }
            if (temperatureLevelCheck(forecastWeatherObject)) {
                alerts.add(new WeatherAlertObject(forecastWeatherObject.dt_txt, WeatherAlertsEnum.TEMPERATURE_MAX, pasiekaId));
            }
            if (extremeConditions(forecastWeatherObject)) {
                alerts.add(new WeatherAlertObject(forecastWeatherObject.dt_txt, WeatherAlertsEnum.EXTREME_CONDITIONS, pasiekaId));
            }
        }
    }

    private boolean humidityLevelCheck(ForecastWeatherObject fwo) {
        return fwo.getMain().getHumidity() > HUMIDITY_MAX_LEVEL || fwo.getMain().getHumidity() < HUMIDITY_MIN_LEVEL;
    }

    private boolean windLevelCheck(ForecastWeatherObject fwo) {
        return fwo.wind.speed>WIND_MAX_SPEED;
    }
    private boolean temperatureLevelCheck(ForecastWeatherObject fwo) {
        return fwo.getMain().getTemp()> MAX_TEMPERATURE_KELVIN;
    }
    private boolean extremeConditions(ForecastWeatherObject fwo) {
        return extremeConditions.contains(fwo.cod);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    private void writeAlertsToDB(Map<WeatherAlertsEnum,List<WeatherAlertObject>> groupByType, WeatherAlertsEnum enumType) {

        Map<Long, List<WeatherAlertObject>> alertTypeByPasiekaId = groupByType.get(enumType).stream().collect(Collectors.groupingBy(WeatherAlertObject::getPasiekaId));
        Map<Long, List<WeatherAlertObject>> AlertsByPasiekaIdDistinctDates=new HashMap<>();
        alertTypeByPasiekaId.
                forEach((k,v)-> AlertsByPasiekaIdDistinctDates.put(k,v.stream().filter(distinctByKey(WeatherAlertObject::getDt_txt_time)).collect(Collectors.toList())));

        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");

            AlertsByPasiekaIdDistinctDates.forEach((k, v) -> {
                Pasieka p1 = pasiekaServiceImpl.getPasiekaById(k);
                v.forEach(x -> {
                    try {
                        alertPogodowyService.addAlertPogoddowy(new AlertPogodowy(convertTypeToDisplayedText(x.getType().toString()), formatter1.parse(x.getDt_txt_time()), p1));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            });


    }

    private void deleteAllWeatherData(){
        alertPogodowyService.deleteAllWeatherAlerts();
    }

    private String convertTypeToDisplayedText (String type) {

        switch (type) {
            case "HUMIDITY":
                return "wysoka wilgotność";
            case "WIND":
                return "silny wiatr";
            case "TEMPERATURE_MAX":
                return "upał!";
            case "EXTREME_CONDITIONS":
                return "warunki extremalne";
        }
        return "";
    }


}
