package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.AlertSpolecznosciowy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertSpolecznosciowyRepository extends JpaRepository<AlertSpolecznosciowy, Long> {


    @Modifying
    @Query(value="delete from alert_spolecznosciowy where alert_spolecznosciowy_id = ?", nativeQuery = true)
    int deleteByPasiekaId(Long alertId);
}
