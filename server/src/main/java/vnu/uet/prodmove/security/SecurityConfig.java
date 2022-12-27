package vnu.uet.prodmove.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.custom.CustomAuthenticationProvider;
import vnu.uet.prodmove.custom.CustomUserDetailsService;
import vnu.uet.prodmove.enums.UserRole;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, ApiConfig.SIGN_UP, ApiConfig.LOG_IN).permitAll()
                .antMatchers(ApiConfig.MODERATOR).hasAuthority(UserRole.MODERATOR.toString())
                .antMatchers(ApiConfig.AGENCY).hasAuthority(UserRole.AGENCY.toString())
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                System.out.println("====>  unauthenticated");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            });

        http.httpBasic();
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                // .authenticationProvider(customAuthenticationProvider()).build();
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}