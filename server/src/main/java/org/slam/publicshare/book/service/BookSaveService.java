package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.domain.Account;
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

    @Transactional
    public Book save(SaveBookRequest dto, Account account) {
        var category = categoryFindService.findByName(dto.getCategory());
        var book = dto.toEntity(account);
        book.setCategory(category);
        return bookRepository.save(book);
    }

}
