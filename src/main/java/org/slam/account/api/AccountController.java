package org.slam.account.api;

import lombok.RequiredArgsConstructor;
import org.slam.account.dto.AccountResponse;
import org.slam.account.service.AccountFindService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountFindService accountFindService;

    @GetMapping("/{id}")
    public AccountResponse findAccountById(@PathVariable Long id) {
        return new AccountResponse(accountFindService.findById(id));
    }

}
