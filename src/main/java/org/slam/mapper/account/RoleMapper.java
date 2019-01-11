package org.slam.mapper.account;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.slam.dto.account.Role;

import java.util.List;

public interface RoleMapper {

    @Select("SELECT ID, NAME FROM ROLE WHERE ID = #{id}")
    Role findById(Long id);

    @Select("SELECT ID, NAME FROM ROLE")
    List<Role> findAll();

    @Insert("INSERT INTO ROLE (ID, NAME) VALUES (#{id}, #{name})")
    void save(Role role);

}