package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.Waga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WagaRepository extends JpaRepository<Waga, Long> {

    @Override
    @Query(
            value = "select w from Waga w left join fetch w.ul"
    )

    List<Waga> findAll();

    @Modifying
    @Transactional
    @Query(
            value = "update waga set producent = ?1, model = ?2 where waga_id = ?3",
            nativeQuery = true
    )
    int updateWagaByWagaId(String producent, String model, Long wagaId);

    @Modifying
    @Transactional
    @Query(
            value = "delete from waga where waga_id = ?1",
             nativeQuery = true
    )
    int deleteWagaById(Long wagaId);

    @Modifying
    @Transactional
    @Query(
            value = "update ul set waga_id = null where waga_id = ?",
            nativeQuery = true
    )
    void removeWagaIdFromAssociatedUlByWagaId(Long wagaId);


    @Modifying
    @Transactional
    @Query(value = "delete from dane_wagowe where waga_id=?", nativeQuery = true)
    void removeAssociaatedDataFromScalestData(Long wagaId);



    @Modifying
    @Transactional
    @Query(value = "update ul set waga_id=? where ul_id=?", nativeQuery = true)
    void updateRelationWithUl(Long wagaId, Long UlId);

    @Query(value = "select 1 from waga\n" +
            "inner join ul on ul.waga_id=waga.waga_id\n" +
            "inner join pasieka p on ul.pasieka_id = p.pasieka_id\n" +
            "inner join uzytkownik u on p.uzytkownik_id = u.uzytkownik_id\n" +
            "inner join uzytkownik_aplikacji ua on u.uzytkownik_aplikacji_id = ua.uzytkownik_aplikacji_id\n" +
            "where ul.waga_id=? and ua.username=?", nativeQuery = true)
    String checkUserPermissions(Long wagaId, String username);
}

