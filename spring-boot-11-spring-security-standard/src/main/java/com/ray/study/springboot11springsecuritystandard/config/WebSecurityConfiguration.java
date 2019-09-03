package com.ray.study.springboot11springsecuritystandard.config;

import com.ray.study.springboot11springsecuritystandard.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurity配置类
 *
 * @author shira 2019/08/30 17:05
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return new BCryptPasswordEncoder();
    }


    /**
     * 基于内存的认证
     *      即认证信息保存在内存中
     * @param auth
     * @throws Exception
     */
    private void inMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("zhangsan").password(passwordEncoder().encode("zhangsan")).roles("USER");
    }


    @Bean
    UserDetailsService detailsService() {
        return new UserDetailsServiceImpl();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 1.基于内存的认证
        //inMemoryAuthentication(auth);

        // 2.基于数据库的认证
        auth.userDetailsService(detailsService())
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sign-up","/registry").permitAll()
                .anyRequest().authenticated()
            .and().formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/login").defaultSuccessUrl("/personal-center",true)
                .failureUrl("/sign-in?error").permitAll()
            .and().logout().logoutSuccessUrl("/sign-in").permitAll()
            .and().rememberMe().tokenValiditySeconds(1209600)
            .and().csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }


}
