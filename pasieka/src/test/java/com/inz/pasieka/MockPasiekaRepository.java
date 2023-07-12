package com.inz.pasieka;

import com.inz.pasieka.tmpPakiet.entities.Pasieka;
import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.repositories.PasiekaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class MockPasiekaRepository implements PasiekaRepository {

    @Override
    public Set<Pasieka> findAllPasieki() {
        return new HashSet<>();
    }

    @Override
    public List<Pasieka> findAllPasiekiForUser(String username) {
        return null;
    }

    @Override
    public int deleteAssociationsWithAlertPogodowy(Long pasiekaId) {
        return 0;
    }

    @Override
    public int deleteAssociationsWithAlertSpolecznosciowy(Long pasiekaId) {
        return 0;
    }

    @Override
    public int deleteAssociationsWithPokarm(Long pasiekaId) {
        return 0;
    }

    @Override
    public String checkUserPermissions(Long pasiekaId, String username) {
        return null;
    }

    @Override
    public int updatePasiekaById(String nazwa, Double lat, Double lon, Long cityId, String cityName, long pasiekaId) {
        return 0;
    }

    @Override
    public int checkIfPasiekaExists(Long pasiekaId) {
        return 0;
    }

    @Override
    public void updatePasiekaNoteData(LocalDate date, String note, Long pasiekaId) {

    }

    @Override
    public void purgePasiekaNoteData(Long pasiekaId) {

    }

    @Override
    public List<Pasieka> findAll() {
        return null;
    }

    @Override
    public List<Pasieka> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Pasieka> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Pasieka> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Pasieka entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Pasieka> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Pasieka> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Pasieka> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Pasieka> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Pasieka> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Pasieka> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Pasieka> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Pasieka getOne(Long aLong) {
        return null;
    }

    @Override
    public Pasieka getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Pasieka> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Pasieka> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Pasieka> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Pasieka> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Pasieka> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Pasieka> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Pasieka, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
