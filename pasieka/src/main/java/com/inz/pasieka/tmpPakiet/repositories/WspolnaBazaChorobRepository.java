package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Choroba;
import com.inz.pasieka.tmpPakiet.entities.WspolnaBazaChorob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Repository
public interface WspolnaBazaChorobRepository extends JpaRepository<WspolnaBazaChorob, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from wspolna_baza_chorob where data_wprowadzenia < ?", nativeQuery = true)
    int deleteByDate(Instant i1);
}
