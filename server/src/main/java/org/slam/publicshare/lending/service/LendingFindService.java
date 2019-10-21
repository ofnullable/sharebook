package org.slam.publicshare.lending.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.common.dto.PageRequest;
import org.slam.publicshare.lending.domain.Lending;
import org.slam.publicshare.lending.domain.LendingStatus;
import org.slam.publicshare.lending.exception.NoSuchLendingException;
import org.slam.publicshare.lending.repository.LendingRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LendingFindService {

    private final LendingRepository lendingRepository;

    public Page<Lending> findAllByAccountIdAndCurrentStatus(Long accountId, LendingStatus lendingStatus, PageRequest pageRequest) {
        return lendingRepository.findAllByAccountIdAndCurrentStatus(accountId, lendingStatus, pageRequest.of());
    }

    public Lending findById(Long lendingId) {
        return lendingRepository.findById(lendingId)
                .orElseThrow(() -> new NoSuchLendingException(lendingId));
    }

}
