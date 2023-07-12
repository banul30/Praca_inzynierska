package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Ul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UlRepository extends JpaRepository<Ul,Long> {

    @Query(
            value = "select u from Ul u " +
                    "left join fetch u.matkaPszczela " +
                    "left join fetch u.pasieka " +
                    "left join fetch u.waga"
    )

    List<Ul> findAllUle();


    @Query(value = "select * from ul where pasieka_id=?", nativeQuery = true)
    Set<Ul> findByPasiekaId(Long pasiekaId);

//    @Modifying
//    @Transactional
//    @Query(value="delete from ul where ul.ul_id =:ulId", nativeQuery = true)
//    void deleteUlById(@Param("ulId")Long ulId);

    @Query(value="select count(*) from ul where ul.pasieka_id = :pasiekaId", nativeQuery = true)
    int countNumberOfUlInPasieka(@Param("pasiekaId")Long pasiekaId);

    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(
            value = "delete from choroba_ul where ul_id = ?",
            nativeQuery = true
    )
    int deleteAssociationsWithChoroba(Long ulId);

    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(
            value = "delete from alert_ul where ul_id = ?",
            nativeQuery = true
    )
    int deleteAssociationsWithAlert(Long ulId);

    @Query("select 'x' from Ul u " +
            "inner join u.pasieka p " +
            "inner join p.uzytkownik uz " +
            "inner join uz.uzytkownikAplikacji ua " +
            "where u.ulId = ?1 and ua.username = ?2 ")
    String checkUserPermissions(Long ulId, String username);

    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value="update ul set nazwa= ?, poziom_agresji=?, rodzaj_korpusu=?, rodzaj_ramek=?, rasa=? where ul_id = ? ", nativeQuery = true)
    int updateUlById(String nazwa, String poziomAgresji, String rodzajKorpusu, String rodzajRamek, String rasa, Long ulId);
    //bez update asoscjaji bo trzeba się zastanowić co wgl chcemy

    @Modifying
    @Query(value = "insert into choroba_ul values (?,?) ", nativeQuery = true)
    int addChorobaToUl(Long chorobaId, Long ulId);

    @Modifying
    @Query(value = "delete from choroba_ul where choroba_id=? and ul_id=? ", nativeQuery = true)
    int deleteChorobaById(Long chorobaId, Long ulId);

    @Query(value="select count(*) from choroba_ul where choroba_id=? and ul_id=? ", nativeQuery = true)
    int findByChorobaId(Long chorobaId, Long pasiekaID);

}
