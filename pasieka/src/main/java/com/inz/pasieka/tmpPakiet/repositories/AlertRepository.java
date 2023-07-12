package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    @Modifying
    @Transactional
    @Query(value="insert into alert_ul values (?,?)", nativeQuery = true)
    void linkAlertWithUl(Long alertId, Long ulId);

    @Modifying
    @Transactional
    @Query(value="delete from alert_ul where alert_id=? and ul_id=?", nativeQuery = true)
    void deleteAlertFormUl(Long alertId, Long ulId);


//    @Modifying
//    @Transactional
//    @Query(value = "update alert set informacja =? where alert_id=?", nativeQuery = true)
//    int updateAlertById(String informacja,Long alertId);
//    @Modifying
//    @Transactional
//    @Query(
//            value = "delete from alert where alert_id=?",
//            nativeQuery = true
//    )
//    int deleteAlertById(Long alertId);
//
//    @Modifying
//    @Transactional
//    @Query(
//            value = "delete from alert_ul where alert_id=?",
//            nativeQuery = true
//    )
//    int deleteAssociationsWithUl(Long alertId);
}
