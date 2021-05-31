package com.example.challenge2_alkemy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsServiceImpl uServiceImpl;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/login").permitAll();
        
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/", "/login").permitAll()
        .antMatchers("/auth/**").hasAnyAuthority("USER")
        .anyRequest().authenticated()

        .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/auth/", true).failureUrl("/login?error=true")
            .loginProcessingUrl("/login-post").permitAll()
        .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }
    
}
