package com.inz.pasieka.ExternalServicesAPI.Scales;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ScalesWeightData {
    double maxWeight;
    double minWeight;
    LocalDate dateForInternalUse;
}
