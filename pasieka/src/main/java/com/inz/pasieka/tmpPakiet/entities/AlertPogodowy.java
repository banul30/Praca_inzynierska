package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertPogodowy {

    @Id
    @SequenceGenerator(
            name = "alert_pogodowy_sequence",
            sequenceName = "alert_pogodowy_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "alert_pogodowy_sequence"
    )
    private Long alertPogodowyId;


    @Column(
            name = "typ",
            columnDefinition = "VARCHAR(30) NOT NULL"
    )
    private String typ;


    @Column(
            name = "data",
            columnDefinition = "DATE"
    )
    private Date data;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "alert_pogodowy_pasieka",
//            joinColumns = @JoinColumn(name = "alert_pogodowy_id", referencedColumnName = "alertPogodowyId", foreignKey = @ForeignKey( name = "FK_alert_pogodowy_pasieka" )),
//            inverseJoinColumns = @JoinColumn(name = "pasieka_id", referencedColumnName = "pasiekaId", foreignKey = @ForeignKey(name = "FK_pasieka_alert_pogodowy"))
//    )
//    @JsonIgnore
//    private Set<Pasieka> pasiekaSet;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pasieka_id", referencedColumnName = "pasiekaId", foreignKey = @ForeignKey(name = "fk_alertPogodowy_pasieka"))
    @JsonIgnore
    Pasieka pasiekaAlert;


    public AlertPogodowy(String typ, Date data, Pasieka pasiekaAlert) {
        this.typ = typ;
        this.data = data;
        this.pasiekaAlert=pasiekaAlert;
    }
}
