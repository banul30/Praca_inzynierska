package com.inz.pasieka.tmpPakiet.security.utilities;

import com.inz.pasieka.tmpPakiet.repositories.*;
import com.inz.pasieka.tmpPakiet.security.repositories.UzytkownikAplikacjiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCheckUtils {

    private final MatkaPszczelaRepository matkaPszczelaRepository;
    private final PasiekaRepository pasiekaRepository;
    private final PokarmRepository pokarmRepository;
    private final UlRepository ulRepository;
    private final WagaRepository wagaRepository;

    @Autowired
    public UserCheckUtils(MatkaPszczelaRepository matkaPszczelaRepository, PasiekaRepository pasiekaRepository, PokarmRepository pokarmRepository, UlRepository ulRepository, WagaRepository wagaRepository) {
        this.matkaPszczelaRepository = matkaPszczelaRepository;
        this.pasiekaRepository = pasiekaRepository;
        this.pokarmRepository = pokarmRepository;
        this.ulRepository = ulRepository;
        this.wagaRepository = wagaRepository;
    }

    public boolean checkMatkaPszczelaPermissions(Long matkaId, String username){
        String s = matkaPszczelaRepository.checkUserPermissions(matkaId, username);
        return s != null;
    }

    public boolean checkUlPermissions(Long ulId, String username){
        String s = ulRepository.checkUserPermissions(ulId, username);
        return s != null;
    }

    public boolean checkPasiekaPermissions(Long pasiekaId, String username){
        String s = pasiekaRepository.checkUserPermissions(pasiekaId, username);
        return s != null;
    }

    public boolean checkPokarmPermissions(Long pokarmId, String username){
        String s = pokarmRepository.checkUserPermissions(pokarmId, username);
        return s != null;
    }

    public boolean checkWagaPermissions(Long wagaId, String username){
        String s = wagaRepository.checkUserPermissions(wagaId, username);
        return s != null;
    }





}
