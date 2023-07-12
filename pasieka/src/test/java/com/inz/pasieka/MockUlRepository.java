package com.inz.pasieka;

import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.repositories.UlRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class MockUlRepository implements UlRepository {
    @Override
    public List<Ul> findAllUle() {
        return null;
    }

    @Override
    public Set<Ul> findByPasiekaId(Long pasiekaId) {
        return null;
    }

    @Override
    public int countNumberOfUlInPasieka(Long pasiekaId) {
        return 0;
    }

    @Override
    public int deleteAssociationsWithChoroba(Long ulId) {
        return 0;
    }

    @Override
    public int deleteAssociationsWithAlert(Long ulId) {
        return 0;
    }

    @Override
    public String checkUserPermissions(Long ulId, String username) {
        return null;
    }

    @Override
    public int updateUlById(String nazwa, String poziomAgresji, String rodzajKorpusu, String rodzajRamek, String rasa, Long ulId) {
        return 0;
    }

    @Override
    public int addChorobaToUl(Long chorobaId, Long ulId) {
        return 0;
    }

    @Override
    public int deleteChorobaById(Long chorobaId, Long ulId) {
        return 0;
    }

    @Override
    public int findByChorobaId(Long chorobaId, Long pasiekaID) {
        return 0;
    }

    @Override
    public List<Ul> findAll() {
        return null;
    }

    @Override
    public List<Ul> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Ul> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Ul> findAllById(Iterable<Long> longs) {
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
    public void delete(Ul entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Ul> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Ul> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Ul> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Ul> findById(Long aLong) {
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
    public <S extends Ul> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Ul> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Ul> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Ul getOne(Long aLong) {
        return null;
    }

    @Override
    public Ul getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Ul> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Ul> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Ul> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Ul> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Ul> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Ul> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Ul, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
