package org.slam.mapper.account;

import org.apache.ibatis.annotations.Update;
import org.slam.dto.account.AccountDto;

public interface AccountUpdateMapper {

    @Update("UPDATE ACCOUNT SET PASSWORD = #{password} WHERE USERNAME = #{username}")
    void updatePassword(AccountDto account);

}