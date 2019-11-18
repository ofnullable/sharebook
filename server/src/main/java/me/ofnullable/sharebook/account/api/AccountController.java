package me.ofnullable.sharebook.account.api;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.account.domain.Email;
import me.ofnullable.sharebook.account.dto.AccountResponse;
import me.ofnullable.sharebook.account.dto.SignUpRequest;
import me.ofnullable.sharebook.account.dto.UpdateAccountRequest;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.account.service.AccountSaveService;
import me.ofnullable.sharebook.account.service.AccountUpdateService;
import me.ofnullable.sharebook.account.service.AccountVerifyService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountFindService accountFindService;
    private final AccountSaveService accountSaveService;
    private final AccountUpdateService accountUpdateService;
    private final AccountVerifyService accountVerifyService;

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse saveAccount(@RequestBody @Valid SignUpRequest dto) {
        return new AccountResponse(accountSaveService.saveAndSignIn(dto));
    }

    @GetMapping("/account/duplicate")
    public boolean duplicateCheck(@Valid Email email) {
        return accountFindService.existedEmail(email);
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

    @PutMapping("/account/{id}")
    public AccountResponse updateAccount(
            @AuthenticationPrincipal(expression = "account") Account account,
            @RequestBody @Valid UpdateAccountRequest dto) {
        if (!account.getId().equals(dto.getId())) {
            throw new IllegalArgumentException("자신의 정보만 수정할 수 있습니다.");
        }
        if (!account.isVerified()) {
            throw new IllegalStateException("비밀번호 인증 후 정보수정이 가능합니다.");
        }
        return new AccountResponse(accountUpdateService.update(dto));
    }

    @PostMapping("/account/verify")
    public AccountResponse verifyAccount(
            @AuthenticationPrincipal(expression = "account") Account account,
            @RequestBody @NotBlank String password) {
        return new AccountResponse(accountVerifyService.verify(account, password));
    }
}
