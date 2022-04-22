package com.example.noSql.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .antMatcher("/**")
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                        .antMatchers(HttpMethod.GET, "/api/articles").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/articles/*").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                // Activate httpBasic Authentication https://en.wikipedia.org/wiki/Basic_access_authentication
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }
}
