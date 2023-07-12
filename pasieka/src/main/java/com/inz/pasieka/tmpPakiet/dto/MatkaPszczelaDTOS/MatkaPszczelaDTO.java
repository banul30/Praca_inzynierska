package com.inz.pasieka.tmpPakiet.dto.MatkaPszczelaDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class MatkaPszczelaDTO {

    LocalDate dataWprowadzenia;
    String rodzajPozyskania;
}
