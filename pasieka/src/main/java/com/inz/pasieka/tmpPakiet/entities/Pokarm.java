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
public class Pokarm {
    @Id
    @SequenceGenerator(
            name = "pokarm_sequence",
            sequenceName = "pokarm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pokarm_sequence"
    )
    private Long PokarmId;

    @Column(
            name = "Rodzaj",
            columnDefinition = "VARCHAR(100) NOT NULL"
    )
    private String Rodzaj; // w przyszłości prawdopodobnie enum
    @Column(
            name = "Masa",
            columnDefinition = "VARCHAR(100) NOT NULL"
    )
    private int Masa;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "pokarm_pasieka",
//            joinColumns = @JoinColumn(name = "pokarm_id", referencedColumnName = "pokarmId", foreignKey = @ForeignKey( name = "FK_pokarm_pasieka" )),
//            inverseJoinColumns = @JoinColumn(name = "pasieka_id", referencedColumnName = "pasiekaId", foreignKey = @ForeignKey(name = "FK_pasieka_pokarm"))
//    )
//    @JsonIgnore
//    private Set<Pasieka> pasiekaSetPokarm;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "pasieka_id",
            referencedColumnName = "pasiekaId"
    )
    @JsonIgnore
    private Pasieka pasiekaPokarm;


}
