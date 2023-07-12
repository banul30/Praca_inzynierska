package com.inz.pasieka.tmpPakiet.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Predykcja {

    @Id
    @SequenceGenerator(
            name = "predykcja_sequence",
            sequenceName = "predykcja_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "predykcja_sequence"
    )
    private Long predykcjaId;

    @Column(
           name = "wspolczynnik",
            columnDefinition = "VARCHAR(10) NOT NULL"
    )
    private String wspolczynnik;

    @OneToMany(mappedBy = "predykcja", fetch = FetchType.LAZY)
    private Set<Pasieka> pasiekaSet;

}
