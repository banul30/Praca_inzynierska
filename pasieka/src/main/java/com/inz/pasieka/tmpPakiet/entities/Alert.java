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
public class Alert {
    @Id
    @SequenceGenerator(
            name = "alert_sequence",
            sequenceName = "alert_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "alert_sequence"
    )
    private Long alertId;

    @Column(
            name = "informacja",
            columnDefinition = "VARCHAR(150) NOT NULL"
    )
    private String informacja;


    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(
            name = "alert_ul",
            joinColumns = @JoinColumn(name = "alert_id", referencedColumnName = "alertId", foreignKey = @ForeignKey( name = "FK_alert_ul" )),
            inverseJoinColumns = @JoinColumn(name = "ul_id", referencedColumnName = "ulId", foreignKey = @ForeignKey(name = "FK_ul_alert"))
    )
    @JsonIgnore
    private Set<Ul> ulSetAlert;


}
