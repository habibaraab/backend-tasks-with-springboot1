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
public class SecurityConfig extends WebSecurityConfigurerAdapter {


        @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication().withUser("Habiba").password(passwordEncoder().encode("12345")).roles("ADMIN")
                .and().withUser("Ali").password(passwordEncoder().encode("1234")).roles("EDITOR")
                .and().withUser("Ahmed").password(passwordEncoder().encode("123")).roles("VIEWER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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


    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}