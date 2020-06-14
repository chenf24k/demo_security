package com.example.spring_security.service;

import com.example.spring_security.auth.MyUserDetails;
import com.example.spring_security.mapper.UserDetailsMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    @Resource
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 加载用户信息
        MyUserDetails userDetails = userDetailsMapper.findByUsername(username);

        // 加载用户角色列表
        List<String> roleCodes = userDetailsMapper.findRoleByUsername(username);

        // 通过角色列表加载用户资源权限列表
        List<String> authorities = userDetailsMapper.findAuthorityByRoleCodes(roleCodes);

        userDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", authorities)
                )
        );
        return userDetails;
    }
}
