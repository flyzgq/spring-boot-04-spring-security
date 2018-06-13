package com.fly.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 *
 * @author  fly
 * @date 2018/5/7 21:28:41
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**","/js/**", "/fonts/**", "/index").permitAll()//都可以访问
            .antMatchers("/users/**").hasRole("ADMIN")//需要相应的角色才可以访问
            .and()
            .formLogin() //基于form表单登陆验证
            .loginPage("/login").failureUrl("/login-error");//自定义登陆界面
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()//认证信息存储在内存中
            .withUser("fly").password("123456").roles("ADMIN");
    }
}
