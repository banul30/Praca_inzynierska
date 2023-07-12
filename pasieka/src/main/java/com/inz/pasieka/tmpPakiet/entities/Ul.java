package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ul {

    @Id
    @SequenceGenerator(
            name = "ul_sequence",
            sequenceName = "ul_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ul_sequence"
    )
    Long ulId;

    @Column(
            name = "nazwa",
            columnDefinition = "VARCHAR(50) NOT NULL"
    )
    String nazwa;

    @Column(
            name = "rodzaj_ramek",
            columnDefinition = "VARCHAR(50) NOT NULL"
    )
    String rodzajRamek;     // w przyszlosic enum //////////////////////////////////  a może jednak nie?

    @Column(
            name = "rodzaj_korpusu",
            columnDefinition = "VARCHAR(50) NOT NULL"
    )
    String rodzajKorpusu;   // w przyszlosic enum //////////////////////////////////  a może jednak nie?

    @Column(
            name = "poziom_agresji",
            columnDefinition = "VARCHAR(30) NOT NULL"
    )
    String poziomAgresji;   // w przyszlosic enum ////////////////////////////////// a może jednak nie?

    @Column(
            name = "rasa",
            columnDefinition = "VARCHAR(50) NOT NULL"
    )
    String rasa;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pasieka_id", referencedColumnName = "pasiekaId", foreignKey = @ForeignKey(name = "fk_ul_pasieka"))
    @JsonIgnore

    Pasieka pasieka;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "matka_pszczela_id", referencedColumnName = "matkaPszczelaId", foreignKey = @ForeignKey(name = "fk_ul_matka_pszczela"))
    MatkaPszczela matkaPszczela;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "waga_id", referencedColumnName = "wagaId", foreignKey = @ForeignKey(name = "fk_ul_waga"))
    Waga waga;

    @ManyToMany(mappedBy = "ulSetChoroba")
    private Set<Choroba> chorobaSet;

    @ManyToMany(mappedBy = "ulSetAlert",  cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private Set<Alert> alertSet;

    public void setAlertSet(Set<Alert> alertSet) {
        this.alertSet = alertSet;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setRodzajRamek(String rodzajRamek) {
        this.rodzajRamek = rodzajRamek;
    }

    public void setRodzajKorpusu(String rodzajKorpusu) {
        this.rodzajKorpusu = rodzajKorpusu;
    }

    public void setPoziomAgresji(String poziomAgresji) {
        this.poziomAgresji = poziomAgresji;
    }

    public void setPasieka(Pasieka pasieka) {
        this.pasieka = pasieka;
    }

    public void setMatkaPszczela(MatkaPszczela matkaPszczela) {
        this.matkaPszczela = matkaPszczela;
    }

    public void setRasa(String rasa) {
        this.rasa=rasa;
    }
    public void getRasa(String rasa) {
        this.rasa=rasa;
    }




    @Override
    public int hashCode() {
        return Objects.hashCode(ulId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ul other = (Ul) obj;
        return Objects.equals(ulId, other.getUlId());
    }

}
