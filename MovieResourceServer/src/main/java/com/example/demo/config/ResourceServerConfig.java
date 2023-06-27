package com.example.demo.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    //auth
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("http requests start");
        http.authorizeRequests().antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/movies").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/v1/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/users/{userId}").access(
                        "@userSecurity.hasUserId(authentication,#userId)");


        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        super.configure(http);
    }
}
