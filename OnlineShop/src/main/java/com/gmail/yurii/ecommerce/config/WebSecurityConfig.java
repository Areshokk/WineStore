package com.gmail.yurii.ecommerce.config;

import com.gmail.yurii.ecommerce.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Service object for working with registered users.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * Service object for encoding passwords.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Configuring rules for user access to site pages.
     * The addresses of resources with limited access are indicated.
     *
     * @param http object of the {@link HttpSecurity} for setting access rights to pages.
     * @throws Exception exception methods of the {@link HttpSecurity} class.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/",
                            "/search",
                            "/registration",
                            "/contacts",
                            "/img/**",
                            "/static/**",
                            "/activate/*",
                            "/product/*",
                            "/menu/**",
                            "/uploads/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and().csrf().disable();
    }

    /**
     * Setting up users with their roles. Users will be loaded from the database
     * using the implementation of the {@link UserDetailsService} interface methods.
     *
     * @param auth object of the {@link AuthenticationManagerBuilder}.
     * @throws Exception exception methods of the {@link AuthenticationManagerBuilder}.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}