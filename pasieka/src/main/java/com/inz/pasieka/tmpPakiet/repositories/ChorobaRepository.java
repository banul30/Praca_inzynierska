package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Choroba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ChorobaRepository extends JpaRepository<Choroba, Long> {
    @Modifying
    @Transactional
    @Query(value = "update choroba set nazwa =?, opis =?, aktywna=? where choroba_id=?", nativeQuery = true)
    int updateChorobaById(String nazwa, String opis, Boolean aktywna, Long id );

    @Modifying
    @Transactional
    @Query(
            value = "delete from choroba where choroba_id=?",
            nativeQuery = true
    )
    int deleteChorobaById(Long chorobaId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from choroba_ul where choroba_id=?",
            nativeQuery = true
    )
    int deleteAssociationsWithUl(Long chorobaId);
}
