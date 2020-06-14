package com.example.spring_security.config;

import com.example.spring_security.auth.AuthenticationFailureHandler;
import com.example.spring_security.auth.AuthenticationSuccessHandler;
import com.example.spring_security.auth.ExpiredSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 开启表单登录
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                // .defaultSuccessUrl("/index")
                // failureUrl("/login.html")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()

                // 资源访问控制
                .authorizeRequests()
                .antMatchers("/login.html", "/login").permitAll()
                .antMatchers("/biz1", "/biz2").hasAnyAuthority("ROLE_user", "ROLE_admin")
                // .antMatchers("/syslog", "/sysuser").hasAnyAuthority("ROLE_admin")
                // 角色是一种特殊的权限
                // .hasAnyRole("admin") 效果等同于 .hasAnyAuthority("ROLE_admin")
                // 通过资源的权限id 类进行配置
                .antMatchers("/syslog").hasAuthority("sys:log")
                .antMatchers("/sysuser").hasAuthority("sys:user")
                .anyRequest().authenticated()
                .and()
                // session 配置
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login.html")// session 失效时 指定重新登录的页面
                .sessionFixation().migrateSession()
                .maximumSessions(1) // 最大允许同时登录数为一个
                .maxSessionsPreventsLogin(false)// 允许再次登录，之前的登录会下线
                .expiredSessionStrategy(new ExpiredSessionStrategy())
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456")).roles("user")
                .and()
                .withUser("admin").password(passwordEncoder().encode("123456"))
                // .roles("admin")
                // 配置 资源的权限id
                .authorities("sys:log", "sys:user")
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
