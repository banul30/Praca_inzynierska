package com.inz.pasieka.tmpPakiet.dto.WagaDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DaneWagoweDTO {
    double waga;
    LocalDate date;
    String pv;
    String amt;
}
