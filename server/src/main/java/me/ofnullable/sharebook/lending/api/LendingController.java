package me.ofnullable.sharebook.lending.api;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.common.dto.PageRequest;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import me.ofnullable.sharebook.lending.dto.LendingResponse;
import me.ofnullable.sharebook.lending.service.LendingFindService;
import me.ofnullable.sharebook.lending.service.LendingSaveService;
import me.ofnullable.sharebook.lending.service.LendingUpdateService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LendingController {

    private final LendingFindService lendingFindService;
    private final LendingSaveService lendingSaveService;
    private final LendingUpdateService lendingUpdateService;

    @PostMapping("/book/{bookId}/lending")
    @ResponseStatus(HttpStatus.CREATED)
    public LendingResponse lendingRequest(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable Long bookId) {
        return new LendingResponse(lendingSaveService.borrowRequest(bookId, account.getId()));
    }

    @PutMapping("/lending/{id}")
    public LendingResponse changeLendingStatus(@PathVariable Long id, @RequestBody LendingStatus status) {
        return new LendingResponse(lendingUpdateService.updateLending(id, status));
    }

    @GetMapping("/account/lendings/{lendingStatus}")
    public Page<LendingResponse> findLendingByAccount(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable LendingStatus lendingStatus,
            @Valid PageRequest pageRequest) {
        return lendingFindService.findAllByAccountIdAndCurrentStatus(account.getId(), lendingStatus, pageRequest)
                .map(LendingResponse::new);
    }

}
