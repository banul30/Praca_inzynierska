package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.MatkaPszczela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatkaPszczelaRepository extends JpaRepository<MatkaPszczela, Long> {

    @Override
    @Query(value = "select m from MatkaPszczela m left join fetch m.ul")
    List<MatkaPszczela> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "update matka_pszczela set data_wprowadzenia = ?1, rodzaj_pozyskania = ?2 where matka_pszczela_id = ?3",
            nativeQuery = true
    )
    int updateMatkaPszczelaById(LocalDate dataWprowadzenia, String rodzajPozyskania, Long matkaId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from matka_pszczela where matka_pszczela_id = ?1",
            nativeQuery = true
    )
    int deleteAllByMatkaPszczelaId(Long matkaId);

    @Modifying
    @Transactional
    @Query(
            value = "update ul set matka_pszczela_id = null where matka_pszczela_id = ?1",
            nativeQuery = true
    )
    void removeMatkaPszcelaIdFromAssociatedUlByMatkaId(Long matkaId);

    @Query("select 'x' from MatkaPszczela m " +
            "inner join m.ul u " +
            "inner join u.pasieka p " +
            "inner join p.uzytkownik uz " +
            "inner join uz.uzytkownikAplikacji ua " +
            "where m.matkaPszczelaId = ?1 and ua.username = ?2 ")
    String checkUserPermissions(Long matkaId, String username);


    @Modifying
    @Query(value = "update ul set matka_pszczela_id=? where ul_id=?", nativeQuery = true)
    void updateRelationWithUl(Long matkaPszczelaId, Long pasiekaId);
}
