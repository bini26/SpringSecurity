package com.spring.security.config;

import com.spring.security.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//user for @PreAuthorize("hasRole('ADMIN')")
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                //.antMatchers("/public/**").permitAll()
               .antMatchers("/public/**").hasRole("Normal")
                .antMatchers("/user/**").hasRole("Admin")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/user/");
//                .and()
//                .httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //when we use nopasswordencoder
      //  auth.inMemoryAuthentication().withUser("Binod").password("@binod").roles("Admin");

        //using password encoding     useing inMemoryAuthentication by initialize in code
//        auth.inMemoryAuthentication().withUser("User").password(this.passwordEncoder().encode("@user")).roles("Normal");
//
//        auth.inMemoryAuthentication().withUser("Binod").password(this.passwordEncoder().encode("@binod")).roles("Admin");


        //fetching user detail from database ans configuring
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
      //without encoding password
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder(10);
    }
}
