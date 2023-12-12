package com.test.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authConfig -> {
                authConfig.requestMatchers(HttpMethod.GET, "/", "/login", "/error", "/login-error", "/logout", "/css/**").permitAll();
                authConfig.requestMatchers(HttpMethod.GET, "/user").hasRole("USER");
                authConfig.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");
            })
            .formLogin(login -> {
                        login.loginPage("/login");
                        login.defaultSuccessUrl("/");
                        login.failureUrl("/login-error");
            })
            .logout(logout -> {
                logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                logout.logoutSuccessUrl("/");
                logout.deleteCookies("JSESSIONID");
                logout.invalidateHttpSession(true);
            });
        return http.build();
    }


}
