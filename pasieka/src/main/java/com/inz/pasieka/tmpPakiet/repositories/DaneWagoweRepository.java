package com.inz.pasieka.tmpPakiet.repositories;

import com.inz.pasieka.tmpPakiet.entities.DaneWagowe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaneWagoweRepository extends JpaRepository<DaneWagowe, Long> {

    @Query(value = "Select * from dane_wagowe where waga_id=? order by date", nativeQuery = true)
     List<DaneWagowe> getByWagaID(Long wagaId);
}
