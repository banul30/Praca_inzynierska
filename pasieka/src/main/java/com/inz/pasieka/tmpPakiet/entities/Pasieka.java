package com.inz.pasieka.tmpPakiet.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

 public class Pasieka {

    @Id
    @SequenceGenerator(
            name = "pasieka_sequence",
            sequenceName = "pasieka_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pasieka_sequence"
    )

    Long pasiekaId;

    @Column(
            name = "nazwa",
            columnDefinition = "VARCHAR(50) NOT NULL"
    )
    String nazwa;


    @Column(
            name = "lat",
            columnDefinition = "float8 not null"
    )
    Double lat;

    @Column(
            name = "lon",
            columnDefinition = "float8 not null"
    )
    Double lon;


    @Column(
            name = "city_id",
            columnDefinition = "INTEGER"
    )
    Long cityId;

    @Column(
            name = "city_name",
            columnDefinition = "VARCHAR(50)"
    )
    String cityName;

    @Column(
            name = "note",
            columnDefinition = "VARCHAR(450)"
    )
    String note;


    @Column(
            name = "note_reminder",
            columnDefinition = "DATE"
    )
    LocalDate noteReminder;

    @OneToMany(mappedBy = "pasieka",fetch = FetchType.LAZY)
    Set<Ul> ule;


    @OneToMany(mappedBy = "pasiekaAlert",fetch = FetchType.LAZY)
    Set<AlertPogodowy> alertPogodowySet;



    @OneToOne(mappedBy = "pasiekaAlertSpolecznosciowy",fetch = FetchType.LAZY)
    AlertSpolecznosciowy alertSpolecznosciowy;

    @OneToOne(mappedBy = "pasiekaPokarm")
    private Pokarm pokarm;


    @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "predykcja_id", referencedColumnName = "predykcjaId", foreignKey = @ForeignKey(name = "fk_pasieka_predykcja"))
   @JsonIgnore
    private Predykcja predykcja;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "uzytkownik_id", referencedColumnName = "uzytkownikId",
                foreignKey = @ForeignKey(
                        name = "fk_pasieka_uzytkownik",
                        foreignKeyDefinition = "FOREIGN KEY (uzytkownik_id) REFERENCES uzytkownik(uzytkownik_id) ON DELETE CASCADE"))//sposób na obejście problemów z orphan removal, pytanie czy tak chcemy
   @JsonIgnore
    private Uzytkownik uzytkownik;

   @Override
   public int hashCode() {
      return Objects.hashCode(pasiekaId);
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Pasieka other = (Pasieka) obj;
      return Objects.equals(pasiekaId, other.getPasiekaId());
   }



}
