package com.example.spring_security.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RBACServiceMapper {

    @Select("SELECT m.menu_url\n" +
            "FROM sys_menu m\n" +
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id\n" +
            "LEFT JOIN sys_role r ON r.id = rm.role_id\n" +
            "LEFT JOIN sys_user_role ur ON r.id = ur.role_id\n" +
            "LEFT JOIN sys_user u ON u.id = ur.user_id \n" +
            "WHERE u.username = #{userId}")
    List<String> findUrlsByUsername(@Param("userId") String userId);

}
