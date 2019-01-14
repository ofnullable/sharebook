package org.slam.service.account;

import lombok.AllArgsConstructor;
import org.slam.dto.account.Account;
import org.slam.dto.account.AccountDetails;
import org.slam.mapper.account.AccountSelectMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AccountSelectService implements UserDetailsService {

    private final AccountSelectMapper accountSelectMapper;

    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(accountSelectMapper.findById(username))
                .map(AccountDetails::new)
                .orElseThrow( () -> new UsernameNotFoundException("Can not find user. username : " + username) );
    }

    public List<Account> findAll() {
        return accountSelectMapper.findAll();
    }

    public void remove(Account account) {
        accountSelectMapper.delete(account);
    }

}