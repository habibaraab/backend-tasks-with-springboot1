package com.mycompany.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomerDetails();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/books", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/books", "POST")).hasAnyRole("ADMIN", "EDITOR")
                        .requestMatchers(new AntPathRequestMatcher("/books/**", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/books/**", "PUT")).hasAnyRole("ADMIN", "EDITOR")
                        .requestMatchers(new AntPathRequestMatcher("/books/**", "DELETE")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .csrf().disable();

        return http.build();
    }
}