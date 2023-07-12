package com.inz.pasieka.tmpPakiet.security.repositories;

import com.inz.pasieka.tmpPakiet.security.entities.UzytkownikAplikacji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UzytkownikAplikacjiRepository extends JpaRepository<UzytkownikAplikacji, Long> {

    UzytkownikAplikacji findByUsername(String mail);

    @Modifying
    @Transactional
    @Query(
            value = "update uzytkownik_aplikacji set access_token = ?1, refresh_token = ?2 where username = ?3",
            nativeQuery = true
    )
    int setTokens(String accessToken, String refreshToken, String username);

    @Query(
            value = "select access_token from uzytkownik_aplikacji where username = ?1",
            nativeQuery = true
    )
    String getAccessToken(String username);

    @Query(
            value = "select refresh_token from uzytkownik_aplikacji where username = ?1",
            nativeQuery = true
    )
    String getRefreshToken(String username);

    @Modifying
    @Transactional
    @Query(
            value = "update uzytkownik_aplikacji set password = ?1 where username = ?2",
            nativeQuery = true
    )
    int changePassword(String newPassword, String username);

    @Query(
            value = "select password from uzytkownik_aplikacji where username = ?1",
            nativeQuery = true
    )
    String getPassword(String username);

    @Modifying
    @Transactional
    @Query(
            value = "update uzytkownik_aplikacji set access_token = null, refresh_token =null where username = ?1",
            nativeQuery = true
    )
    void logout(String username);
}
