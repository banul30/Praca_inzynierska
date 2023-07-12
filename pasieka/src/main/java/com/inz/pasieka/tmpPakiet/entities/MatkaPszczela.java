package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class MatkaPszczela {

    @Id
    @SequenceGenerator(
            name = "matka_pszczela_sequence",
            sequenceName = "matka_pszczela_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "matka_pszczela_sequence"
    )
    Long matkaPszczelaId;

    @Column(
            name = "data_wprowadzenia",
            columnDefinition = "TIMESTAMPTZ NOT NULL"
    )
    LocalDate dataWprowadzenia;  //ewentualnie LocalDate, do przemyślenia

    @Column(
            name = "rodzaj_pozyskania",
            columnDefinition = "VARCHAR(80) NOT NULL"
    )
    String rodzajPozyskania;

    @OneToOne(mappedBy = "matkaPszczela", fetch = FetchType.LAZY)
    @JsonIgnore
    Ul ul;

}
