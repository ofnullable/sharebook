package me.ofnullable.sharebook.lending.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import me.ofnullable.sharebook.lending.exception.LendingStatusInvalidException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LendingUpdateService {

    private final LendingFindService lendingFindService;

    @Transactional
    public Lending updateLending(Long id, LendingStatus lendingStatus) {
        var lending = lendingFindService.findById(id);
        switch (lendingStatus) {
            case CANCELED:
                lending.canceled();
                break;
            case ACCEPTED:
                lending.accept();
                break;
            case REJECTED:
                lending.rejected();
                break;
            case RETURNED:
                lending.returned();
                break;
            default:
                throw new LendingStatusInvalidException(LendingStatus.NONE, lendingStatus);
        }
        return lending;
    }

}
