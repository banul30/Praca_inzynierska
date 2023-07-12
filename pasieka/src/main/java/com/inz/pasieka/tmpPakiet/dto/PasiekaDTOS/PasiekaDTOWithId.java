package com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS;

import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasiekaDTOWithId {
    String nazwa;
    Double lat;
    Double lon;
    Long pasiekaId;
    boolean healthy;
    boolean atmosphericStatus;
    int liczbaUli;
    int liczbaAlertow;
    boolean alertSpolecznosciowyExistance;
    String cityName;


    public PasiekaDTOWithId setHealthyChain(boolean healthy) {
        this.healthy = healthy;
        return this;
    }

    public PasiekaDTOWithId setAtmosphericStatusChain(boolean atmosphericStatus) {
        this.atmosphericStatus = atmosphericStatus;
        return this;
    }

    public PasiekaDTOWithId setLiczbaUli(Pasieka p1) {
        this.liczbaUli = p1.getUle().size();
        return this;
    }
    public PasiekaDTOWithId setLiczbaAlertow(int liczbaAlertow) {
        this.liczbaAlertow = liczbaAlertow;
        return this;
    }

    public PasiekaDTOWithId setAlertSpolecznosciowyExistance(Pasieka p1) {
        this.alertSpolecznosciowyExistance= p1.getAlertSpolecznosciowy() != null;
        return this;
    }
    public PasiekaDTOWithId setCityNameChain(String cityName) {
        this.cityName=cityName;
        return this;
    }


}
