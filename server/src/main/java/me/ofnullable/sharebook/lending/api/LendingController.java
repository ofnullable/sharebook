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

    @PostMapping("/lending/book/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LendingResponse borrowRequest(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable Long bookId) {
        return new LendingResponse(lendingSaveService.borrowRequest(bookId, account.getId()));
    }

    @PutMapping("/lending/{id}/{status}")
    public LendingResponse changeLendingStatus(@PathVariable Long id, @PathVariable LendingStatus status) {
        return new LendingResponse(lendingUpdateService.updateLending(id, status));
    }

    @GetMapping("/lending/book/{bookId}/latest")
    public LendingResponse findLatestLendingByBookId(@PathVariable Long bookId) {
        return new LendingResponse(lendingFindService.findLatestByBookId(bookId));
    }

    @GetMapping("/lending/{status}")
    public Page<LendingResponse> findLendingByAccount(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable LendingStatus status,
            @Valid PageRequest pageRequest) {
        return lendingFindService.findAllByAccountIdAndCurrentStatus(account.getId(), status, pageRequest)
                .map(LendingResponse::new);
    }

}
