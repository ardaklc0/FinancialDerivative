package com.test.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authConfig -> {
                authConfig.requestMatchers(HttpMethod.GET, "/index", "/register", "/login", "/error", "/login-error", "/logout", "/css/**").permitAll();
                authConfig.requestMatchers(HttpMethod.POST,"/save").permitAll();
                authConfig.requestMatchers(HttpMethod.GET, "/user/**", "/role/**", "/option/**", "/risk-neutral/**").hasAnyRole("USER", "ADMIN");
                authConfig.requestMatchers(HttpMethod.POST,"/user/**", "/role/**", "/option/**", "/risk-neutral/**").hasAnyRole("USER", "ADMIN");
                authConfig.requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN");
                authConfig.requestMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN");
            })
            .formLogin(login -> {
                        login.loginPage("/login");
                        login.defaultSuccessUrl("/index");
                        login.failureUrl("/login-error");
            })
            .logout(logout -> {
                logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                logout.logoutSuccessUrl("/login");
                logout.deleteCookies("JSESSIONID");
                logout.invalidateHttpSession(true);
            });
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder());
        return auth;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
