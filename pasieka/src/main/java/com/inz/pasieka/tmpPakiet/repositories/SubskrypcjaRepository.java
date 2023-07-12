package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Subskrypcja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubskrypcjaRepository extends JpaRepository<Subskrypcja,Long> {

    @Override
    @Query(
            value = "select s from Subskrypcja s"
    )
    List<Subskrypcja> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "delete from subskrypcja where subskrypcja_id = ?1",
            nativeQuery = true
    )
    int deleteAllSubskrypcjaById(Long subskrypcjaId);

}
