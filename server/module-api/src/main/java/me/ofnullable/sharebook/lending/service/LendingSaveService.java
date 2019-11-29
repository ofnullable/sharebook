package me.ofnullable.sharebook.lending.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.repository.LendingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LendingSaveService {

    private final LendingValidator validator;
    private final BookFindService bookFindService;
    private final LendingRepository lendingRepository;

    @Transactional
    public Lending borrowRequest(Long bookId, Long accountId) {
        var book = bookFindService.findById(bookId);
        var lending = Lending.builder().book(book).accountId(accountId).build();
        validator.validate(lending);
        lending.borrow();
        return lendingRepository.save(lending);
    }

}
