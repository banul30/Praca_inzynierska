package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static org.hibernate.annotations.FetchMode.SELECT;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DaneWagowe {
    @Id
    @SequenceGenerator(
            name = "scales_sequence",
            sequenceName = "scales_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "scales_sequence"
    )
    private Long scalesDataId;

    @Column(
            name = "max_weight",
            columnDefinition = "float8 not null"
    )
    private double maxWeight;

    @Column(
            name = "min_weight",
            columnDefinition = "float8"
    )
    private double minWeight;

    @Column(
            name = "date",
            columnDefinition = "date not null"
    )
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waga_id", referencedColumnName = "wagaId", foreignKey = @ForeignKey(name = "fk_dane_wagowe_waga"))
    @JsonIgnore
    private Waga waga;


    public DaneWagowe(double maxWeight, double minWeight, LocalDate date, Waga waga) {
        this.maxWeight = maxWeight;
        this.minWeight = minWeight;
        this.date = date;
        this.waga = waga;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(scalesDataId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DaneWagowe other = (DaneWagowe) obj;
        return Objects.equals(scalesDataId, other.getScalesDataId());
    }


}
