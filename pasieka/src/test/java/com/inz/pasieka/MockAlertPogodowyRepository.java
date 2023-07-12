package com.inz.pasieka;

import com.inz.pasieka.tmpPakiet.entities.AlertPogodowy;
import com.inz.pasieka.tmpPakiet.repositories.AlertPogodowyRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockAlertPogodowyRepository implements AlertPogodowyRepository {
    @Override
    public List<AlertPogodowy> findAll() {
        return null;
    }

    @Override
    public List<AlertPogodowy> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<AlertPogodowy> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<AlertPogodowy> findAllById(Iterable<Long> longs) {
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
    public void delete(AlertPogodowy entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AlertPogodowy> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends AlertPogodowy> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AlertPogodowy> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AlertPogodowy> findById(Long aLong) {
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
    public <S extends AlertPogodowy> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends AlertPogodowy> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<AlertPogodowy> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public AlertPogodowy getOne(Long aLong) {
        return null;
    }

    @Override
    public AlertPogodowy getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends AlertPogodowy> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends AlertPogodowy> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends AlertPogodowy> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends AlertPogodowy> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends AlertPogodowy> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends AlertPogodowy> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends AlertPogodowy, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public int checkAlertExistanceByPasiekaId(Long pasiekaId) {
        return 0;
    }

    @Override
    public void deleteByPasiekaId(Long pasiekaId) {

    }
}
