package com.inz.pasieka;

import com.inz.pasieka.tmpPakiet.entities.Ul;
import com.inz.pasieka.tmpPakiet.services.UlServiceInterface;

public class MockUlServiceInterface implements UlServiceInterface {
    @Override
    public void deleteUl(Long ulId) {

    }

    @Override
    public Ul getUlById(Long ulId) {
        return new Ul();
    }
}
