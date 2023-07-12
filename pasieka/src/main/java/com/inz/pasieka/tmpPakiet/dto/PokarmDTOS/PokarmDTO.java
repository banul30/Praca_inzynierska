package com.inz.pasieka.tmpPakiet.dto.PokarmDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokarmDTO {
    String rodzaj;
    int masa;
    long pasiekaId;
}
