package com.example.sbsecuritytest.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MyConfig {

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> requests.requestMatchers("/").permitAll().anyRequest().authenticated());

        // for stateless session creation
        // http.sessionManagement(session ->
        // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults()); 
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user1").password("{noop}mypassword").roles("user").build();
        UserDetails admin = User.withUsername("admin").password("{noop}mypassword2").roles("admin").build();
        return new InMemoryUserDetailsManager(user1, admin);
    }

}
