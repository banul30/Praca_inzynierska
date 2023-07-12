package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.repositories.RepositoriesInterface.PasiekaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface PasiekaRepository extends JpaRepository<Pasieka,Long>, PasiekaRepositoryInterface {


    @Query(value = "select * from pasieka left join ul on pasieka.pasieka_id = ul.pasieka_id", nativeQuery = true)
    Set<Pasieka> findAllPasieki();

    @Query("select p from Pasieka p " +
            "inner join p.uzytkownik uz " +
            "inner join uz.uzytkownikAplikacji ua " +
            "where ua.username = ?1 ")
    List<Pasieka> findAllPasiekiForUser(String username);

    @Modifying
    @Transactional
    @Query(
            value = "delete from alert_pogodowy where pasieka_id = ?1",
            nativeQuery = true
    )
    int deleteAssociationsWithAlertPogodowy(Long pasiekaId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from alert_spolecznosciowy where pasieka_id = ?1",
            nativeQuery = true
    )
    int deleteAssociationsWithAlertSpolecznosciowy(Long pasiekaId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from pokarm where pasieka_id = ?1",
            nativeQuery = true
    )
    int deleteAssociationsWithPokarm(Long pasiekaId);

    @Query("select 'x' from Pasieka p " +
            "inner join p.uzytkownik uz " +
            "inner join uz.uzytkownikAplikacji ua " +
            "where p.pasiekaId = ?1 and ua.username = ?2 ")
    String checkUserPermissions(Long pasiekaId, String username);

    @Modifying
    @Transactional
    @Query(value = "update pasieka set nazwa=?, lat=?, lon=?, city_id=?,city_name=? where pasieka_id=?", nativeQuery = true)
    int updatePasiekaById(String nazwa, Double lat, Double lon, Long cityId, String cityName, long pasiekaId);

    @Transactional
    @Query(value = "select count(*) from pasieka where pasieka_id=?", nativeQuery = true)
    int checkIfPasiekaExists(Long pasiekaId);

    @Transactional
    @Modifying
    @Query(value = "update pasieka set note_reminder=?, note=? where pasieka_id=?", nativeQuery = true)
    void updatePasiekaNoteData(LocalDate date, String note, Long pasiekaId);

    @Transactional
    @Modifying
    @Query(value = "update pasieka set note_reminder=null, note=null where pasieka_id=?", nativeQuery = true)
    void purgePasiekaNoteData(Long pasiekaId);


}
