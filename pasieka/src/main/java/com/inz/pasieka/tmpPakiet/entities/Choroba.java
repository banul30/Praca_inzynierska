package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Choroba {
    @Id
    @SequenceGenerator(
            name = "choroba_sequence",
            sequenceName = "choroba_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "choroba_sequence"
    )
    private Long chorobaId;

    @Column(
            name="nazwa",
            columnDefinition = "VARCHAR(30)"
    )
    private String nazwa;

    @Column(
            name="opis",
            columnDefinition = "VARCHAR(100)"
    )
    private String opis;

    @Column(
            name="aktywna",
            columnDefinition = "BOOLEAN"
    )
    private Boolean aktywna;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "choroba_ul",
            joinColumns = @JoinColumn(name = "choroba_id", referencedColumnName = "chorobaId", foreignKey = @ForeignKey( name = "FK_choroba_ul" )),
            inverseJoinColumns = @JoinColumn(name = "ul_id", referencedColumnName = "ulId", foreignKey = @ForeignKey(name = "FK_ul_choroba"))
    )
    @JsonIgnore
    private Set<Ul> ulSetChoroba;
}
