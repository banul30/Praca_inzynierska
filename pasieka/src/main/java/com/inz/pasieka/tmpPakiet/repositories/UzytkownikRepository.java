package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UzytkownikRepository extends JpaRepository<Uzytkownik,Long> {

    @Query(
            value = "select * from uzytkownik u join uzytkownik_aplikacji ua on u.uzytkownik_aplikacji_id = ua.uzytkownik_aplikacji_id where ua.username= ?1",
            nativeQuery = true
    )
    Uzytkownik findUzytkownikByUsername(String username);

    @Modifying
    @Transactional
    @Query(
            value = "update uzytkownik u set imie = ?1, nazwisko = ?2 from uzytkownik_aplikacji ua where u.uzytkownik_aplikacji_id = ua.uzytkownik_aplikacji_id and ua.username = ?3",
            nativeQuery = true
    )
    int updateUzytkownikByUsername(String imie, String nazwisko, String username);

    @Modifying
    @Transactional
    @Query(
            value = "delete from uzytkownik where uzytkownik_id = ?1",
            nativeQuery = true
    )
    int deleteAllUzytkownikById(Long uzytkownikId);


    @Query(value = "select username from uzytkownik_aplikacji where uzytkownik_aplikacji_id=\n" +
            "(\n" +
            "    select uzytkownik_aplikacji_id from uzytkownik where uzytkownik_id=\n" +
            "    (select uzytkownik_id from pasieka where pasieka_id=?)\n" +
            ")", nativeQuery = true)
    String getEmailByPasiekaId(Long pasiekaId);

}
