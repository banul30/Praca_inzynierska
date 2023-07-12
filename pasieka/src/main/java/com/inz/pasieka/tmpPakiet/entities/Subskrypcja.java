package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subskrypcja {

    @Id
    @SequenceGenerator(
            name = "subskrypcja_sequence",
            sequenceName = "subskrypcja_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subskrypcja_sequence"
    )
    private Long subskrypcjaId;

    @OneToOne
    @JoinColumn(name = "uzytkownik_id", referencedColumnName = "uzytkownikId", foreignKey = @ForeignKey(name = "fk_subskrypcja_uzytkownik"))
    @JsonIgnore
    private Uzytkownik uzytkownik;
}
