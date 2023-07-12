package com.inz.pasieka.tmpPakiet.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inz.pasieka.tmpPakiet.security.services.UzytkownikAplikacjiService;
import com.inz.pasieka.tmpPakiet.security.utilities.SecurityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final UzytkownikAplikacjiService uzytkownikAplikacjiService;

    public CustomAuthorizationFilter(UzytkownikAplikacjiService uzytkownikAplikacjiService) {
        this.uzytkownikAplikacjiService = uzytkownikAplikacjiService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/tests/sec/token/refresh") || request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/tests/sec/add")){
            filterChain.doFilter(request,response);
        }else{
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                try {
                    String token = authHeader.substring("Bearer ".length());
                    Algorithm algorithm = SecurityUtils.getAlgorithm();
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    if(!uzytkownikAplikacjiService.checkUzytkownikAplikacjiAccessToken(username,token)){
                        throw new JWTVerificationException("Incorrect Token");
                    }
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    Arrays.stream(roles).forEach(role ->{
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response);
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
                filterChain.doFilter(request,response);
            }
        }
    }
}
