package org.slam.mapper.account;

import org.apache.ibatis.annotations.Insert;
import org.slam.dto.account.RoleDto;

public interface RoleMapper {

    @Insert("INSERT INTO ROLE (ID, NAME) VALUES (#{id}, #{name})")
    void save(RoleDto role);

    /*
    @Select("SELECT ID, NAME FROM ROLE WHERE ID = #{id}")
    RoleDto findById(Long id);

    @Select("SELECT ID, NAME FROM ROLE")
    List<RoleDto> findAll();
    */

}