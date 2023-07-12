package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Pokarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface PokarmRepository extends JpaRepository<Pokarm, Long> {
    @Modifying
    @Transactional
    @Query(value="update pokarm set masa =?, rodzaj=? where pokarm_id=?", nativeQuery = true)
    int updatePokarmByPokarmId(int masa, String rodzaj,Long pokarmId);


    @Modifying
    @Transactional
    @Query(
            value = "delete from pokarm where pokarm_id = ?",
            nativeQuery = true
    )
    int deletePokarmByPokarmId(Long pokarmId);


    @Query("select 'x' from Pokarm pok " +
            "inner join pok.pasiekaPokarm p " +
            "inner join p.uzytkownik uz " +
            "inner join uz.uzytkownikAplikacji ua " +
            "where pok.PokarmId = ?1 and ua.username = ?2 ")
    String checkUserPermissions(Long pokarmId, String username);

//    @Modifying
//    @Transactional
//    @Query(
//            value = "delete from pokarm_pasieka where pokarm_id = ?",
//            nativeQuery = true
//    )
//    int deleteAssociationsWithPasieka(Long alertId);




}
