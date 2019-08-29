package org.slam.publicshare.account.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.dto.AccountResponse;
import org.slam.publicshare.account.dto.SignUpRequest;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.account.service.AccountSaveService;
import org.slam.publicshare.account.service.AccountUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountFindService accountFindService;
    private final AccountSaveService accountSaveService;
    private final AccountUpdateService accountUpdateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse saveAccount(@RequestBody @Valid SignUpRequest dto) {
        return new AccountResponse(accountSaveService.save(dto));
    }

    @GetMapping("/{id}")
    public AccountResponse findAccountById(@PathVariable Long id) {
        return new AccountResponse(accountFindService.findById(id));
    }

    @PatchMapping("/{id}")
    public AccountResponse updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        return new AccountResponse(accountUpdateService.updatePassword(id, newPassword));
    }

}
