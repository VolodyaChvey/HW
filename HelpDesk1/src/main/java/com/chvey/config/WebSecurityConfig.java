package com.chvey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /*  @Autowired
      private DataSource dataSource;*/
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                //  .httpBasic()
                // .and()
               .authorizeRequests()
              .antMatchers("/")
               .permitAll()
                // .antMatchers(HttpMethod.POST, "/api/tickets").hasAnyAuthority("EMPLOYEE", "MANAGER")
                // .antMatchers(HttpMethod.PATCH, "/api/tickets/{ticketId}").hasAnyAuthority("EMPLOYEE", "MANAGER")
                // .antMatchers("/api/tickets/{tickedId}/feedbacks").hasAnyAuthority("EMPLOYEE", "MANAGER")

                // .anyRequest()
                // .authenticated()
              .and()
        //.formLogin()
        // .loginPage("/login")
        // .passwordParameter("password")
        // .usernameParameter("userName")
        // .defaultSuccessUrl("/hello", false)
        // .and()
        // .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication()
                .withUser("vasya")
                .password("{noop}password")
                .roles("USER");
    }

}
