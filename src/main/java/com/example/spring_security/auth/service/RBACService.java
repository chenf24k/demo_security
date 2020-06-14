package com.example.spring_security.auth.service;

import com.example.spring_security.auth.mapper.RBACServiceMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("rabcService")
public class RBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private RBACServiceMapper rbacServiceMapper;

    /**
     * 判断用户是否具有该request的访问权限
     *
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            List<String> urls = rbacServiceMapper.findUrlsByUsername(username);
            return urls.stream().anyMatch(
                    url -> antPathMatcher.match(url, request.getRequestURI())
            );
        }

        return false;
    }
}
