package com.inz.pasieka.ExternalServicesAPI.Scales;
import com.inz.pasieka.tmpPakiet.Exception.ExistanceValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScalesServiceBeeHub implements ScalesInterface {
    private final RestTemplate restTemplate;


    public ScalesServiceBeeHub(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ScalesWeightData getData(String Key) {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate todayDate =  LocalDate.now();
        LocalDate yesterdayDate = todayDate.minusDays(1);
        String dateToString= formatter2.format(todayDate);
        String dateFromString=formatter2.format(yesterdayDate);
        String DateTo=dateToString+"%2022:23:59";
        String DateFrom=dateFromString+"%2022:10:01";
        String url="https://api.intelligenthives.eu/api/data/public-analysis?DeviceId=" +
                Key +
                "&DateFrom=" +
                DateFrom +
                "&DateTo=" +
               DateTo;

        ScalesWeightData scalesData;
        try {
        scalesData= restTemplate.getForObject(new URI(url), ScalesWeightData.class);
        }
        catch (Exception ex) {
          throw new ExistanceValidationException("mi ma: "+ ex.getMessage());
        }

        if (scalesData==null || (scalesData.maxWeight==0 && scalesData.minWeight==0)) {
            throw new ExistanceValidationException("nie można pobrać danych wagowych!");
        }
        scalesData.dateForInternalUse=todayDate;
        return scalesData;
    }


    public List<ScalesWeightData> getDataLast7Days(String Key) {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<ScalesWeightData> scalesWeightDataList = new ArrayList<>();

        for (int i=3;i>0;i--) {
            LocalDate todayDate =  LocalDate.now().minusDays(i+1);
            LocalDate yesterdayDate = todayDate.minusDays(i);
            String dateToString= formatter2.format(todayDate);
            String dateFromString=formatter2.format(yesterdayDate);
            String DateTo=dateToString+"%2022:23:59";
            String DateFrom=dateFromString+"%2022:10:01";


            String url="https://api.intelligenthives.eu/api/data/public-analysis?DeviceId=" +
                    Key +
                    "&DateFrom=" +
                    DateFrom +
                    "&DateTo=" +
                    DateTo;

            ScalesWeightData scalesData=null;
            try {
                scalesData= restTemplate.getForObject(new URI(url), ScalesWeightData.class);
            }
            catch (Exception ex) {
                throw new ExistanceValidationException("mi ma: "+ ex.getMessage());
                //todo logger jakiś tutaj!
            }

            if (scalesData==null || (scalesData.maxWeight==0 && scalesData.minWeight==0)) {
                throw new ExistanceValidationException("nie można pobrać danych wagowych!");
            }
            scalesData.dateForInternalUse=todayDate;
            scalesWeightDataList.add(scalesData);
        }

        return scalesWeightDataList;
    }

}
