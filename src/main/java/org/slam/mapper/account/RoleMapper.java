package org.slam.mapper.account;

import org.apache.ibatis.annotations.Insert;
import org.slam.dto.account.Role;

public interface RoleMapper {

    @Insert("INSERT INTO ROLE (ID, NAME) VALUES (#{id}, #{name})")
    void save(Role role);

    /*
    @Select("SELECT ID, NAME FROM ROLE WHERE ID = #{id}")
    Role findById(Long id);

    @Select("SELECT ID, NAME FROM ROLE")
    List<Role> findAll();
    */

}