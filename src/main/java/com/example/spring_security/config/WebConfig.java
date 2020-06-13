package com.example.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启表单登录
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .and()

                // 资源访问控制
                .authorizeRequests()
                .antMatchers("/login.html", "/login").permitAll()
                .antMatchers("/biz1", "/biz2")
                .hasAnyAuthority("ROLE_user", "ROLE_admin")
                .antMatchers("/syslog", "/sysuser")
                .hasAnyAuthority("ROLE_admin")
                // 角色是一种特殊的权限
                // .hasAnyRole("admin")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456")).roles("user")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("123456")).roles("admin")
                .and()
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态资源不需要认证
        web.ignoring()
                .antMatchers("/css/**", "fonts/**", "/img/**", "/js/**");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
