package com.inz.pasieka.tmpPakiet.security.services;

import com.inz.pasieka.tmpPakiet.security.entities.PasswordDTO;
import com.inz.pasieka.tmpPakiet.security.repositories.RolaRepository;
import com.inz.pasieka.tmpPakiet.security.repositories.UzytkownikAplikacjiRepository;
import com.inz.pasieka.tmpPakiet.security.entities.Rola;
import com.inz.pasieka.tmpPakiet.security.entities.UzytkownikAplikacji;
import com.inz.pasieka.tmpPakiet.security.utilities.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedHashSet;

@Service
@Transactional
public class UzytkownikAplikacjiService implements UserDetailsService {

    private final UzytkownikAplikacjiRepository uzytkownikAplikacjiRepository ;
    private final RolaRepository rolaRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UzytkownikAplikacjiService(UzytkownikAplikacjiRepository uzytkownikAplikacjiRepository ,RolaRepository rolaRepository, PasswordEncoder passwordEncoder) {
        this.rolaRepository = rolaRepository;
        this.uzytkownikAplikacjiRepository = uzytkownikAplikacjiRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UzytkownikAplikacji uzytkownikAplikacji = uzytkownikAplikacjiRepository.findByUsername(mail);
        if(uzytkownikAplikacji ==null){
            throw  new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new LinkedHashSet<>();
        uzytkownikAplikacji.getRolaSet().forEach(rola ->{
            authorities.add(new SimpleGrantedAuthority(rola.getNazwa()));
            rola.getUprawnienieSet().forEach(uprawnienie -> authorities.add(new SimpleGrantedAuthority(uprawnienie.getNazwa())));
        });
        return new User(uzytkownikAplikacji.getUsername(), uzytkownikAplikacji.getPassword(), authorities);
    }


    public UzytkownikAplikacji saveUzytkownikAplikacji(UzytkownikAplikacji uzytkownikAplikacji) {
        uzytkownikAplikacji.setPassword(passwordEncoder.encode(uzytkownikAplikacji.getPassword()));
        return uzytkownikAplikacjiRepository.save(uzytkownikAplikacji);
    }


    public void addRolaToUzytkownikAplikacji(String mail, String roleName) {
        UzytkownikAplikacji uzytkownikAplikacji = uzytkownikAplikacjiRepository.findByUsername(mail);
        Rola rola = rolaRepository.findByNazwa(roleName);
        uzytkownikAplikacji.getRolaSet().add(rola);
        rolaRepository.addRoleToUser(rola.getRolaId(), uzytkownikAplikacji.getUzytkownikAplikacjiId());
    }

    public UzytkownikAplikacji getUzytkownikAplikacji(String mail) {
        return uzytkownikAplikacjiRepository.findByUsername(mail);
    }

    public int setUzytkownikAplikacjiTokens(String accessToken, String refreshToken, String username){
        String s = SecurityUtils.encodeText(accessToken);
        String s1 = SecurityUtils.encodeText(refreshToken);
        return uzytkownikAplikacjiRepository.setTokens(SecurityUtils.encodeText(accessToken), SecurityUtils.encodeText(refreshToken), username);
    }

    @Transactional
    public void logout(String username) {
        uzytkownikAplikacjiRepository.logout(username);
    }

    @Transactional
    public ResponseEntity<Object> changePassword(String username, PasswordDTO passwordDTO) {
        //int res = uzytkownikAplikacjiRepository.changePassword(passwordEncoder.encode(passwordDTO.getNewPassword()),passwordEncoder.encode(passwordDTO.getOldPassword()),username);

        if(passwordEncoder.matches(passwordDTO.getOldPassword(), uzytkownikAplikacjiRepository.getPassword(username))){
            uzytkownikAplikacjiRepository.changePassword(passwordEncoder.encode(passwordDTO.getNewPassword()),username);
            return new ResponseEntity<>("Zmieniono Hasło", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Błędne dane", HttpStatus.BAD_REQUEST);
        }
    }


    public boolean checkUzytkownikAplikacjiAccessToken(String username, String accessToken){
        //boolean t = SecurityUtils.encodeText(accessToken).equals(uzytkownikAplikacjiRepository.getAccessToken(username));
        return SecurityUtils.encodeText(accessToken).equals(uzytkownikAplikacjiRepository.getAccessToken(username));
       // return passwordEncoder.matches(accessToken, uzytkownikAplikacjiRepository.getAccessToken(username));
    }

    public boolean checkUzytkownikAplikacjiRefreshToken(String username, String refreshToken){
        return SecurityUtils.encodeText(refreshToken).equals(uzytkownikAplikacjiRepository.getRefreshToken(username));
       // return passwordEncoder.matches(refreshToken, uzytkownikAplikacjiRepository.getRefreshToken(username));
    }


}
