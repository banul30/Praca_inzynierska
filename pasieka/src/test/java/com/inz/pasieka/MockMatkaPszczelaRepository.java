package com.inz.pasieka;

import com.inz.pasieka.tmpPakiet.entities.MatkaPszczela;
import com.inz.pasieka.tmpPakiet.repositories.MatkaPszczelaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockMatkaPszczelaRepository implements MatkaPszczelaRepository {

    @Override
    public List<MatkaPszczela> findAll() {
        return null;
    }

    @Override
    public List<MatkaPszczela> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<MatkaPszczela> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<MatkaPszczela> findAllById(Iterable<Long> longs) {
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
    public void delete(MatkaPszczela entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MatkaPszczela> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends MatkaPszczela> S save(S entity) {
        return null;
    }

    @Override
    public <S extends MatkaPszczela> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MatkaPszczela> findById(Long aLong) {
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
    public <S extends MatkaPszczela> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends MatkaPszczela> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<MatkaPszczela> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public MatkaPszczela getOne(Long aLong) {
        return null;
    }

    @Override
    public MatkaPszczela getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends MatkaPszczela> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MatkaPszczela> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends MatkaPszczela> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends MatkaPszczela> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends MatkaPszczela> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends MatkaPszczela> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends MatkaPszczela, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public int updateMatkaPszczelaById(LocalDate dataWprowadzenia, String rodzajPozyskania, Long matkaId) {
        return 0;
    }

    @Override
    public int deleteAllByMatkaPszczelaId(Long matkaId) {
        return 0;
    }

    @Override
    public void removeMatkaPszcelaIdFromAssociatedUlByMatkaId(Long matkaId) {

    }

    @Override
    public String checkUserPermissions(Long matkaId, String username) {
        return null;
    }

    @Override
    public void updateRelationWithUl(Long matkaPszczelaId, Long pasiekaId) {

    }
}
