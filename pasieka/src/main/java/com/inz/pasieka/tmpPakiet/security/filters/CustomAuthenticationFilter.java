package com.inz.pasieka.tmpPakiet.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.inz.pasieka.tmpPakiet.security.services.UzytkownikAplikacjiService;
import com.inz.pasieka.tmpPakiet.security.utilities.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UzytkownikAplikacjiService uzytkownikAplikacjiService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, UzytkownikAplikacjiService uzytkownikAplikacjiService) {
        this.authenticationManager = authenticationManager;
        this.uzytkownikAplikacjiService = uzytkownikAplikacjiService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String password = SecurityUtils.decodeText(request.getParameter("password"));
        String username = request.getParameter("username");
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return  authenticationManager.authenticate(authToken);
    }

    @Override
    @Transactional
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Algorithm algorithm = SecurityUtils.getAlgorithm();
        User user = (User) authResult.getPrincipal();
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ 15*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        int res = uzytkownikAplikacjiService.setUzytkownikAplikacjiTokens(accessToken, refreshToken, user.getUsername());
        if(res != 1){
            throw  new IOException();
        }
        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed); //TODO
    }
}
