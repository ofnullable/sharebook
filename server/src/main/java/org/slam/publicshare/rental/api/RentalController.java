package org.slam.publicshare.rental.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.rental.dto.RentalResponse;
import org.slam.publicshare.rental.service.RentalFindService;
import org.slam.publicshare.rental.service.RentalSaveService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalFindService rentalFindService;
    private final RentalSaveService rentalSaveService;

    @GetMapping("/rental")
    public List<RentalResponse> findRentalByAccount(@AuthenticationPrincipal(expression = "account") Account account) {
        return rentalFindService.findByAccountId(account.getId()).stream()
                .map(RentalResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/rental/{bookId}")
    public List<RentalResponse> findRentalByBookId(@PathVariable Long bookId) {
        return rentalFindService.findByBookId(bookId).stream()
                .map(RentalResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/rental/{bookId}")
    public RentalResponse rentalRequest(@PathVariable Long bookId, @AuthenticationPrincipal(expression = "account") Account account) {
        return new RentalResponse(rentalSaveService.rentalRequest(bookId, account.getId()));
    }

}
