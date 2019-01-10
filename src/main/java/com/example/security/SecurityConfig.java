package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Administrator on 2017/2/13.
 *  @EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置
 * 需要扩展WebSecurityConfigurerAdapter
 * @EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，如最常用的@PreAuthorize
 * 注销接口
 *http://127.0.0.1:8080/login?logout    method:get
 *http://127.0.0.1:8080/login       method:post，Content-Type：application/x-www-form-urlencoded
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //static final String ADMIN_AUTH = "1";
    //static final String USER_AUTH = "0";

    //不知道这里为什么@Autowired报红,jinhua的例子这里也是报红，但是运行没影响
    @Autowired
    private AuthProvider provider;

    //重写注解
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/user", "/users", "/users/*").permitAll()
                //.antMatchers("/admin/*").hasAnyAuthority(ADMIN_AUTH)
                .anyRequest().authenticated()
                .and()
             .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
             .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)  throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER");
        auth.authenticationProvider(provider);
    }
}
