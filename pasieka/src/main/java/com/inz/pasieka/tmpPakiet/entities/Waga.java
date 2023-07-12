package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.hibernate.annotations.FetchMode.SELECT;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Waga {

    @Id
    @SequenceGenerator(
            name = "waga_sequence",
            sequenceName = "waga_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "waga_sequence"
    )
    Long wagaId;

    @Column(
            name = "producent",
            columnDefinition = "VARCHAR(80) NOT NULL"
    )
    String producent;

    @Column(
            name = "model",
            columnDefinition = "VARCHAR(40) NOT NULL"
    )
    String model;

    @Column(
            name = "apiID",
            columnDefinition = "VARCHAR(160) NOT NULL"
    )
    String apiID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="waga_id")
    @JsonIgnore
    Ul ul;

    @OneToMany(mappedBy = "waga",fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.PERSIST }) //o w morde to w końcu działa! o co kaman xd
    private Set<DaneWagowe> scalesData;


    @Override
    public int hashCode() {
        return Objects.hashCode(wagaId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Waga other = (Waga) obj;
        return Objects.equals(wagaId, other.getWagaId());
    }



}
