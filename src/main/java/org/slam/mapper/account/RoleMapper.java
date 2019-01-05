package org.slam.mapper.account;

import org.apache.ibatis.annotations.Select;
import org.slam.dto.account.Role;

public interface RoleMapper {
	
	@Select("SELECT ID, NAME FROM ROLE WHERE ID = #{id}")
	Role findById(Long id);
	
	void save(Role role);
	
}
