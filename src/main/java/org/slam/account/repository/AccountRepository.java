package org.slam.account.repository;

import org.slam.account.domain.Account;
import org.slam.account.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(Email email);

    boolean existsByEmail(Email email);

}
