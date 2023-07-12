package com.inz.pasieka.tmpPakiet.security.entities;

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
public class Uprawnienie {

    @Id
    @SequenceGenerator(
            name = "uprawnienie_sequence",
            sequenceName = "uprawnienie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "uprawnienie_sequence"
    )
    private Long uprawnienieId;

    @Column(
            name = "nazwa",
            columnDefinition = "VARCHAR(60) NOT NULL"
    )
    private String nazwa;

    @ManyToMany(mappedBy = "uprawnienieSet")
    private Set<Rola> rolaSet;
}
