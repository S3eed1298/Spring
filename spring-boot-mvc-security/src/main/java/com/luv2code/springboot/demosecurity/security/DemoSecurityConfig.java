package com.luv2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //**for custom tables**
        //define query to retrieve a user by username
        //user_id=? will be taken from the login form
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        //define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("" +
                "select user_id, role from roles where user_id=? ");

        return jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(
                // this means for any request coming to the app it must be authenticated
                configurer -> configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(
                //Show our custom form at the request mapping /showMyLoginPage
                //then the login form should POST data to this URL for processing (check user id and password) -> /authenticateTheUser (you can change url but same name in login form )
                //no Controller Request mapping required for this /authenticateTheUser it is a spring thing default to handle checking user id and password
                //put in the form data in Html fields must be named username and password
                // permitAll to let everyone see the login page( in order to let them login)
                form -> form.loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll()
        ).logout(
                // add logout support for default url /logout
                logout -> logout.permitAll()
        ).exceptionHandling(
                configurer -> configurer
                        .accessDeniedPage("/access-denied")
        );

        return http.build();
    }
}
