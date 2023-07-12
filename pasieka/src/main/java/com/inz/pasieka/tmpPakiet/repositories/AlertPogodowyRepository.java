package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.AlertPogodowy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AlertPogodowyRepository extends JpaRepository<AlertPogodowy, Long> {

//    @Query(
//            value = "select a from AlertPogodowy a where a.lokalizacja = ?1"
//    )
//    List<AlertPogodowy> findAllByLokalizacja(String localization);

    @Override
    @Query(
            value = "select a from AlertPogodowy  a"
    )
    List<AlertPogodowy> findAll();

//    @Modifying
//    @Transactional
//    @Query(
//            value = "update alert_pogodowy set lokalizacja = ?1, informacja = ?2 where alert_pogodowy_id = ?3",
//            nativeQuery = true
//    )
//    int updateAlertPogodowyByAlertId(String lokalizacja, String informacja, Long alertId);



    @Query(value = "select count(*) from alert_pogodowy where pasieka_id = ?", nativeQuery = true)
    int checkAlertExistanceByPasiekaId(Long pasiekaId);


    @Modifying
    @Transactional
    @Query(value = "delete from alert_pogodowy where pasieka_id=?", nativeQuery = true)
     void deleteByPasiekaId(Long pasiekaId);


//    @Modifying
//    @Transactional
//    @Query(
//            value = "delete from alert_pogodowy where alert_pogodowy_id = ?1",
//            nativeQuery = true
//    )
//    int deleteAllAlertPogodowyByAlertId(Long alertId);
//
//    @Modifying
//    @Transactional
//    @Query(
//            value = "delete from alert_pogodowy_pasieka where alert_pogodowy_id = ?1",
//            nativeQuery = true
//    )
//    int deleteAssociationsWithPasieka(Long alertId);
}
