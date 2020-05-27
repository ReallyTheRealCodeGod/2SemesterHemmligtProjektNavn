package com.automobil.webdemoautomobil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

// Tells spring that this is a web security application
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set configuration on the auth object here

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled "
                    + "from users "
                    + "where username = ? ")
                .authoritiesByUsernameQuery("select username,authority "
                    + "from authorities "
                    + "where username = ? ");

        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema() // h2 default database
                .withUser(
                        User.withUsername("user")
                        .password("pass")
                        .roles("USER")
                )
                .withUser(
                        User.withUsername("admin")
                                .password("pass")
                                .roles("ADMIN")
                );
         */

        /* This is an in memory implementation
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("pass")
                .roles("USER")
                .and() // Tilføj flere usernames
                .withUser("admin")
                .password("pass")
                .roles("ADMIN");

         */

    }

    //You need to provide a password encoder for web apps. Don't use NoOpPasswordEncoder in final product as it does not
    //encode the password.
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    //Defining restrictions for different user roles
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Starting with the most restricted path. In this case /admin can only be accessed by a user with the admin role.
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/**", "static/css", "static/img").permitAll() // Accessible by all roles, without authentication
                .and().formLogin();

    }
}
