package org.slam.publicshare.lending.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.lending.domain.Lending;
import org.slam.publicshare.lending.domain.LendingStatus;
import org.slam.publicshare.lending.exception.LendingStatusInvalidException;
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
            case ACCEPTED:
                lending.accept();
                break;
            case REJECTED:
                lending.reject();
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
