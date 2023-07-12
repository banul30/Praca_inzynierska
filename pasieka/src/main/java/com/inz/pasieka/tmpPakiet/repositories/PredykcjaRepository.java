package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Predykcja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PredykcjaRepository extends JpaRepository<Predykcja,Long> {

    @Modifying
    @Transactional
    @Query(
            value = "update predykcja set wspolczynnik = ?1 where predykcja_id = ?2",
            nativeQuery = true
    )
    int updatePredykcjaById(String wspolczynnik, Long predykcjaId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from predykcja where predykcja_id = ?1",
            nativeQuery = true
    )
    int deleteAllByPredykcjaId(Long wspolczynnikId);

}
