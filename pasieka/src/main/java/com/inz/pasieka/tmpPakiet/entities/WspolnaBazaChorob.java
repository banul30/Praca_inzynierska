package com.inz.pasieka.tmpPakiet.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WspolnaBazaChorob {
    @Id
    @SequenceGenerator(
            name = "wspolna_baza_chorob_sequence",
            sequenceName = "wspolna_baza_chorob_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wspolna_baza_chorob_sequence"
    )
    private Long rekordId;



    @Column(
            name = "data_wprowadzenia",
            columnDefinition = "TIMESTAMPTZ"
    )
    LocalDate dataWprowadzenia;

    @Column(
            name = "lat",
            columnDefinition = "float8"
    )
    Double lat;

    @Column(
            name = "lon",
            columnDefinition = "float8"
    )
    Double lon;


}
