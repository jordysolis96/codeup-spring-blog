package com.spring.springblog;

import com.spring.springblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServlet;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader userDetailsLoader;

    public SecurityConfiguration(UserDetailsLoader userDetailsLoader){
        this.userDetailsLoader = userDetailsLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsLoader).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //defines how to login
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/login?logout")
                //define pages where you don't have to be logged in
                .and()
                .authorizeRequests()
                .antMatchers("/", "/sign-up", "/ads", "/posts")
                .permitAll()
                //defines pages that requires users to be logged in
                .and()
                .authorizeRequests()
                .antMatchers("/ads/*", "/posts/*")
                .authenticated();
    }
}
