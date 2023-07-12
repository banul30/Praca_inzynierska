package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inz.pasieka.tmpPakiet.security.entities.UzytkownikAplikacji;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Uzytkownik {

    @Id
    @SequenceGenerator(
            name = "uzytkownik_sequence",
            sequenceName = "uzytkownik_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "uzytkownik_sequence"
    )
    private Long uzytkownikId;

    @Column(
            name = "imie",
            columnDefinition = "VARCHAR(40) NOT NULL"
    )
    private String imie;

    @Column(
            name = "nazwisko",
            columnDefinition = "VARCHAR(50) NOT NULL"
    )
    private String nazwisko;

    @OneToOne(mappedBy = "uzytkownik", fetch = FetchType.LAZY,  cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }, orphanRemoval = true)
    @JsonIgnore
    private Subskrypcja subskrypcja;

    @OneToMany(mappedBy = "uzytkownik",fetch = FetchType.LAZY, orphanRemoval = true, cascade ={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JsonIgnore
    private Set<Pasieka> pasiekaSet;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uzytkownik_aplikacji_id", referencedColumnName = "uzytkownikAplikacjiId", foreignKey = @ForeignKey(name = "fk_uzytkownik_aplikacji_uzytkownik"))
    @JsonIgnore
    private UzytkownikAplikacji uzytkownikAplikacji;



}
