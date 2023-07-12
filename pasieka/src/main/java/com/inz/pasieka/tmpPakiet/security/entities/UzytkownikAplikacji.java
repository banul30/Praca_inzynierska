package com.inz.pasieka.tmpPakiet.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inz.pasieka.tmpPakiet.entities.Uzytkownik;
import com.inz.pasieka.tmpPakiet.security.entities.Rola;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UzytkownikAplikacji {

    @Id
    @SequenceGenerator(
            name = "app_uzytkownik_sequence",
            sequenceName = "app_uzytkownik_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_uzytkownik_sequence"
    )
    private Long uzytkownikAplikacjiId;

    @Column(
            name = "username",
            columnDefinition = "VARCHAR(60) UNIQUE NOT NULL"
    )
    private String username;
    @Column(
            name = "password",
            columnDefinition = "VARCHAR(60) NOT NULL"
    )
    private String password;
    @Column(
            name = "access_token",
            columnDefinition = "VARCHAR(200)"
    )
    private String accessToken;
    @Column(
            name = "refresh_token",
            columnDefinition = "VARCHAR(200)"
    )
    private String refreshToken;

    @ManyToMany(mappedBy = "uzytkownikAplikacjiSet")
    private Set<Rola> rolaSet;

    @OneToOne(mappedBy = "uzytkownikAplikacji", fetch = FetchType.LAZY)
    @JsonIgnore
    private Uzytkownik uzytkownik;

}
