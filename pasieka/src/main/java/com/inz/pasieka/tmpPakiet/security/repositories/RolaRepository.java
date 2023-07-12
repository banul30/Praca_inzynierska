package com.inz.pasieka.tmpPakiet.security.repositories;

import com.inz.pasieka.tmpPakiet.security.entities.Rola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RolaRepository extends JpaRepository<Rola, Long> {

    Rola findByNazwa(String nazwa);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rola_uzytkownik_aplikacji(rola_id, uzytkownik_aplikacji_id) VALUES (?, ?)", nativeQuery = true)
    void addRoleToUser(Long roleId, Long userId);
}
