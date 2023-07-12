package com.inz.pasieka.tmpPakiet.dto.PasiekaDTOS;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PasiekaDTONote {
    Long pasiekaId;
    String note;
    LocalDate date;
}
