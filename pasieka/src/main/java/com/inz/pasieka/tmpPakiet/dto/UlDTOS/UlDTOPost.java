package com.inz.pasieka.tmpPakiet.dto.UlDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UlDTOPost {
    String nazwa;
    String poziomAgresji;
    String rodzajKorpusu;
    String rodzajRamek;
    Long pasiekaId;
    String rasa;
}
