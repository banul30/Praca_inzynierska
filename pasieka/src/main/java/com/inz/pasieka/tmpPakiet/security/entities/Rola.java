package com.inz.pasieka.tmpPakiet.security.entities;

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
@NoArgsConstructor
@AllArgsConstructor
public class Rola {


    @Id
    @SequenceGenerator(
            name = "rola_sequence",
            sequenceName = "rola_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rola_sequence"
    )
    private Long rolaId;

    @Column(
            name = "nazwa",
            columnDefinition = "VARCHAR(60) NOT NULL"
    )
    private String nazwa;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "rola_uzytkownik_aplikacji",
            joinColumns = @JoinColumn(name = "rola_id", referencedColumnName = "rolaId", foreignKey = @ForeignKey( name = "FK_rola_uzytkownik_aplikacji" )),
            inverseJoinColumns = @JoinColumn(name = "uzytkownik_aplikacji_id", referencedColumnName = "uzytkownikAplikacjiId", foreignKey = @ForeignKey(name = "FK_uzytkownik_aplikacji_rola"))
    )
    @JsonIgnore
    private Set<UzytkownikAplikacji> uzytkownikAplikacjiSet;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "rola_uprawnienie",
            joinColumns = @JoinColumn(name = "rola_id", referencedColumnName = "rolaId", foreignKey = @ForeignKey( name = "FK_rola_uprawnienie" )),
            inverseJoinColumns = @JoinColumn(name = "uprawnienie_id", referencedColumnName = "uprawnienieId", foreignKey = @ForeignKey(name = "FK_uprawnienie_rola"))
    )
    @JsonIgnore
    private Set<Uprawnienie> uprawnienieSet;
}
