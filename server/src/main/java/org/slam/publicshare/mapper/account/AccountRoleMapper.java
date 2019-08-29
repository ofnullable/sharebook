package org.slam.publicshare.mapper.account;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface AccountRoleMapper {

    @Insert("INSERT INTO ACCOUNT_ROLE (USERNAME, ROLE_ID) VALUES (#{username}, #{role_id})")
    void save(@Param("username") String username, @Param("role_id") Long roleId);

}