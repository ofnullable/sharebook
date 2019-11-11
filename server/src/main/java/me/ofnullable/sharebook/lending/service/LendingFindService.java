package me.ofnullable.sharebook.lending.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.common.dto.PageRequest;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import me.ofnullable.sharebook.lending.exception.LendingHistoryNotExistsException;
import me.ofnullable.sharebook.lending.repository.LendingRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LendingFindService {

    private final LendingRepository lendingRepository;

    public Page<Lending> findAllByAccountIdAndCurrentStatus(Long accountId, LendingStatus status, PageRequest pageRequest) {
        return lendingRepository.findAllByBorrowerIdAndCurrentStatus(accountId, status, pageRequest.of());
    }

    public Lending findById(Long lendingId) {
        return lendingRepository.findById(lendingId)
                .orElseThrow(() -> new ResourceNotFoundException(lendingId, Lending.class));
    }

    public Lending findLatestByBookId(Long bookId) {
        return lendingRepository.findFirstByBookIdOrderByIdDesc(bookId)
                .orElseThrow(() -> new LendingHistoryNotExistsException(bookId));
    }

    public Lending findLatestByBorrowerIdAndBookId(Long borrowerId, Long bookId) {
        return lendingRepository.findFirstByBorrowerIdAndBookId(borrowerId, bookId)
                .orElseThrow(() -> new LendingHistoryNotExistsException(bookId));
    }

}
