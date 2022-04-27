package com.example.nosql.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                .cors()
                .and()
                .build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("HEAD",
                        "GET", "POST", "PUT", "DELETE", "PATCH");
            }
        };
    }

    @Bean
    public UserDetailsService users(){
        UserDetails maxime= User.builder().username("maxime").password("{noop}maxime").roles("USER").build();
        UserDetails amandine= User.builder().username("amandine").password("{noop}password").roles("USER").build();
        UserDetails maud= User.builder().username("maud").password("{bcrypt}$2y$10$7BCRAh8SE2v53kMGPTqkdOJUEnmaxk4vfqF6DJHfmDKfXwotTdame").roles("MANAGER").build();
        UserDetails aymeric= User.builder().username("aymeric").password("{MD5}5f4dcc3b5aa765d61d8327deb882cf99").roles("MANAGER").build();
        return new InMemoryUserDetailsManager(maxime, amandine, maud,aymeric);
    }
}
