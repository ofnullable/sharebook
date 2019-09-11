package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.dto.book.SaveBookRequest;
import org.slam.publicshare.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookSaveService {

    private final BookRepository bookRepository;
    private final CategoryFindService categoryFindService;
    private final AccountFindService accountFindService;

    @Transactional
    public Book save(SaveBookRequest dto, Long accountId) {
        var account = accountFindService.findById(accountId);
        var category = categoryFindService.findByName(dto.getCategory());
        var book = dto.toEntity(account);
        book.setCategory(category);
        return bookRepository.save(book);
    }

}
