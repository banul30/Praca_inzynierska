package com.inz.pasieka.tmpPakiet.security;

import com.inz.pasieka.tmpPakiet.security.filters.CustomAuthenticationFilter;
import com.inz.pasieka.tmpPakiet.security.filters.CustomAuthorizationFilter;
import com.inz.pasieka.tmpPakiet.security.services.UzytkownikAplikacjiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10, new SecureRandom()); //TODO sprawdzenie czy ta sól działa identycznie po restarcie programu
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, AuthenticationManager authenticationManager, UzytkownikAplikacjiService service) throws Exception{
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager, service);
        customAuthenticationFilter.setFilterProcessesUrl("/api/tests/sec/login");
        return http
                .cors().and()
                .csrf()/*.disable(}*/
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .sessionManagement(s ->{
                    s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeRequests(auth -> {
                    /*auth.anyRequest().permitAll();*/
                    auth.antMatchers("/api/login/**", "/api/tests/sec/**").permitAll();
                    auth.antMatchers("/api/tests/alert/**").hasAuthority("ROLE_ADMIN");
                    auth.antMatchers("/api/tests/alertPogodowy/add/**","api/tests/alertPogodowy/put/**","api/tests/alertPogodowy/delete/**").hasAuthority("ROLE_ADMIN");
                    auth.antMatchers(HttpMethod.GET, "/api/tests/**").hasAuthority("app:read");
                    auth.antMatchers(HttpMethod.POST, "/api/tests/**").hasAuthority("app:write");
                    auth.antMatchers(HttpMethod.PUT, "/api/tests/**").hasAuthority("app:update");
                    auth.antMatchers(HttpMethod.DELETE, "/api/tests/**").hasAuthority("app:delete");
                    auth.anyRequest().authenticated();
                })
                .addFilter(customAuthenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter(service), UsernamePasswordAuthenticationFilter.class)
                .headers( h -> h.frameOptions().disable())
                .build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedHeaders(List.of("X-XSRF-TOKEN", "Authorization", "content-type"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT"));
        configuration.setExposedHeaders(List.of("access_token", "refresh_token", "error"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
