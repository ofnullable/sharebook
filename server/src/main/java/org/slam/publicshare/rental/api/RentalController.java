package org.slam.publicshare.rental.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.common.dto.PageRequest;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.dto.RentalResponse;
import org.slam.publicshare.rental.service.RentalFindService;
import org.slam.publicshare.rental.service.RentalSaveService;
import org.slam.publicshare.rental.service.RentalUpdateService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalFindService rentalFindService;
    private final RentalSaveService rentalSaveService;
    private final RentalUpdateService rentalUpdateService;

    @PostMapping("/book/{bookId}/rental")
    @ResponseStatus(HttpStatus.CREATED)
    public RentalResponse rentalRequest(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable Long bookId) {
        return new RentalResponse(rentalSaveService.rentalRequest(bookId, account.getId()));
    }

    @PutMapping("/rental/{id}")
    public RentalResponse changeRentalStatus(@PathVariable Long id, @RequestBody RentalStatus status) {
        return new RentalResponse(rentalUpdateService.updateRental(id, status));
    }

    @GetMapping("/account/rentals/{rentalStatus}")
    public Page<RentalResponse> findRentalByAccount(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable RentalStatus rentalStatus,
            @Valid PageRequest pageRequest) {
        return rentalFindService.findAllByAccountIdAndCurrentStatus(account.getId(), rentalStatus, pageRequest)
                .map(RentalResponse::new);
    }

}
