package com.inz.pasieka.tmpPakiet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertSpolecznosciowy {
    @Id
    @SequenceGenerator(
            name = "alert_spolecznosciowy_sequence",
            sequenceName = "alert_spolecznosciowy_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "alert_spolecznosciowy_sequence"
    )
    private Long alertSpolecznosciowyId;
    @Column(
            name = "informacja",
            columnDefinition = "VARCHAR(100) NOT NULL"
    )
    private String informacja;

    @Column(
            name = "data",
            columnDefinition = "DATE"
    )
    private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pasieka_id", referencedColumnName = "pasiekaId", foreignKey = @ForeignKey(name = "fk_alertSpolecznosciowy_pasieka"))
    @JsonIgnore
    Pasieka pasiekaAlertSpolecznosciowy;

    public AlertSpolecznosciowy(Date date, String informacja, Pasieka p1) {
        this.date=date;
        this.informacja=informacja;
        this.pasiekaAlertSpolecznosciowy=p1;
    }
}
