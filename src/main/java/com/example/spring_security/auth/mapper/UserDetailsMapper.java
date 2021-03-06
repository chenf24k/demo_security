package com.example.spring_security.auth.mapper;

import com.example.spring_security.auth.MyUserDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDetailsMapper {

    // 根据userId 查询用户信息
    @Select("SELECT username,`password`,enabled FROM sys_user u WHERE u.username = #{userId}")
    MyUserDetails findByUsername(@Param("userId") String userId);

    // 根据userId 查询用户角色列表
    @Select("SELECT r.role_code FROM sys_role r\n" +
            "LEFT JOIN sys_user_role ur ON r.id = ur.role_id\n" +
            "LEFT JOIN sys_user u ON u.id = ur.user_id\n" +
            "WHERE u.username =  #{userId}")
    List<String> findRoleByUsername(@Param("userId") String userId);

    // 根据用户角色查询用户权限
    @Select({
            "<script>",
            "SELECT m.menu_url ",
            "FROM sys_menu m ",
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id ",
            "LEFT JOIN sys_role r ON r.id = rm.role_id ",
            "WHERE r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=', ' close=')'>",
            "#{roleCode}",
            "</foreach>",
            "</script>"
    })
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);

}
