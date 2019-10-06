package org.slam.publicshare.account.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.account.dto.AccountResponse;
import org.slam.publicshare.account.dto.SignUpRequest;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.account.service.AccountSaveService;
import org.slam.publicshare.account.service.AccountUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountFindService accountFindService;
    private final AccountSaveService accountSaveService;
    private final AccountUpdateService accountUpdateService;

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse saveAccount(@RequestBody @Valid SignUpRequest dto) {
        return new AccountResponse(accountSaveService.save(dto));
    }

    @GetMapping("/account/{id}")
    public AccountResponse findAccountById(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable Long id) {
        if (id == 0) { // id가 0이면 현재 세션의 account를 돌려준다.
            return new AccountResponse(account);
        }
        return new AccountResponse(accountFindService.findById(id));
    }

    @PatchMapping("/account/{id}")
    public AccountResponse updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        return new AccountResponse(accountUpdateService.updatePassword(id, newPassword));
    }

}
