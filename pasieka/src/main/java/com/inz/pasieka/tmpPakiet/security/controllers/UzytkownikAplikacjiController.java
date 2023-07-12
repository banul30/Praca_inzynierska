package com.inz.pasieka.tmpPakiet.security.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inz.pasieka.tmpPakiet.entities.Pokarm;
import com.inz.pasieka.tmpPakiet.entities.Uzytkownik;
import com.inz.pasieka.tmpPakiet.security.entities.PasswordDTO;
import com.inz.pasieka.tmpPakiet.security.entities.Rola;
import com.inz.pasieka.tmpPakiet.security.entities.UzytkownikAplikacji;
import com.inz.pasieka.tmpPakiet.security.entities.UzytkownikAplikacjiDTO;
import com.inz.pasieka.tmpPakiet.security.services.UzytkownikAplikacjiService;
import com.inz.pasieka.tmpPakiet.security.utilities.SecurityUtils;
import com.inz.pasieka.tmpPakiet.services.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "api/tests/sec")
public class UzytkownikAplikacjiController {

    private final UzytkownikAplikacjiService uzytkownikAplikacjiService;
    private final UzytkownikService uzytkownikService;

    @Autowired
    public UzytkownikAplikacjiController(UzytkownikAplikacjiService uzytkownikAplikacjiService, UzytkownikService uzytkownikService) {
        this.uzytkownikAplikacjiService = uzytkownikAplikacjiService;
        this.uzytkownikService = uzytkownikService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> handleLogin(){
        return new ResponseEntity<>("Logged successfully", HttpStatus.OK);
    }

    @GetMapping("/csrf")
    public ResponseEntity<Object> handleCsrf(){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Object> addNewUzytkownikAplikacji(@RequestBody UzytkownikAplikacjiDTO userDTO) {
        UzytkownikAplikacji user = new UzytkownikAplikacji(null, userDTO.getUsername(), SecurityUtils.decodeText(userDTO.getPassword()) , null, null, new HashSet<>(), null);
        Uzytkownik uzytkownik = new Uzytkownik(null, "Default", "Default", null,null,user);
        uzytkownikAplikacjiService.saveUzytkownikAplikacji(user);
        uzytkownikAplikacjiService.addRolaToUzytkownikAplikacji(user.getUsername(), "ROLE_USER");
        uzytkownikService.addUzytkownik(uzytkownik);
        return new ResponseEntity<>("Uzytkownik aplikacji created!", HttpStatus.CREATED);
    }

    @PutMapping("/password/change")
    @Transactional
    public ResponseEntity<Object> changeUzytkownikAplikacjiHaslo(@RequestBody PasswordDTO passwordDTO) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        passwordDTO.setOldPassword(SecurityUtils.decodeText(passwordDTO.getOldPassword()));
        passwordDTO.setNewPassword(SecurityUtils.decodeText(passwordDTO.getNewPassword()));
        return uzytkownikAplikacjiService.changePassword(username, passwordDTO);
    }

    @PutMapping("/logout")
    @Transactional
    public ResponseEntity<Object> handleLogout() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        uzytkownikAplikacjiService.logout(username);
        return new ResponseEntity<>("Wylogowano ", HttpStatus.OK);
    }

    @GetMapping("/token/refresh")
    @Transactional
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = SecurityUtils.getAlgorithm();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                if(!uzytkownikAplikacjiService.checkUzytkownikAplikacjiRefreshToken(username,refresh_token)){
                    throw new JWTVerificationException("Incorrect Token");
                }
                UzytkownikAplikacji user = uzytkownikAplikacjiService.getUzytkownikAplikacji(username);
                List<String> authorities = new ArrayList<>();
                user.getRolaSet().forEach(rola ->{
                    authorities.add(rola.getNazwa());
                    rola.getUprawnienieSet().forEach(uprawnienie -> authorities.add(uprawnienie.getNazwa()));
                });
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", authorities)
                        .sign(algorithm);
                uzytkownikAplikacjiService.setUzytkownikAplikacjiTokens(accessToken, refresh_token, username);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refresh_token);
                response.setHeader("access_token", accessToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                // response.sendError(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw  new RuntimeException("Refresh tokne is missing");
        }

    }



}
