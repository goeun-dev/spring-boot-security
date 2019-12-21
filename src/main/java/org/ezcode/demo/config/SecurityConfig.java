package org.ezcode.demo.config;

import javax.sql.DataSource;

import org.ezcode.demo.security.CustomLoginSuccessHandler;
import org.ezcode.demo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Setter(onMethod_ = {@Autowired})
    public DataSource dataSource;


    
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     log.info("configure--------------------------------");
    //     auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");     // 내부적으로 접두어 'ROLE_'이 붙음

        
    //     // $2a$10$okrJIOZNAbyX9Ocs8ox58.zJW3SWvMf9m6o9PxCmIIEtIUUv12l1y
    //     auth.inMemoryAuthentication()
    //     .withUser("member")
    //     .password("$2a$10$okrJIOZNAbyX9Ocs8ox58.zJW3SWvMf9m6o9PxCmIIEtIUUv12l1y")
    //     .roles("MEMBER");
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserService())
        .passwordEncoder(passwordEncoder());

        // log.info("configure JDBC--------------------------");

        // String queryUser = "select userid, userpw, enabled from tbl_member where userid = ?";
        // String queryDetails = "select userid, auth from tbl_member_auth where userid = ?";

        // auth.jdbcAuthentication()
        // .dataSource(dataSource)
        // .passwordEncoder(passwordEncoder())
        // .usersByUsernameQuery(queryUser)
        // .authoritiesByUsernameQuery(queryDetails);
    }

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/member/all").permitAll()
        .antMatchers("/member/admin").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/member/member").access("hasRole('ROLE_MEMBER')");

        // http.authorizeRequests()
        // .antMatchers("/member/all").permitAll()
        // .antMatchers("/member/member").hasRole("MEMBER");

        http.formLogin()
        .loginPage("/customLogin")
        .loginProcessingUrl("/login")
        .successHandler(loginSuccessHandler());

        http.logout()
        .logoutUrl("/customLogout")
        .invalidateHttpSession(true)
        .deleteCookies("remember-me", "JESSION_ID");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    
}