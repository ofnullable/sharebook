package me.ofnullable.sharebook.book.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.book.dto.book.SaveBookRequest;
import me.ofnullable.sharebook.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookSaveService {

    private final BookRepository bookRepository;
    private final CategoryFindService categoryFindService;

    @Transactional
    public Long save(SaveBookRequest dto, Account account) {
        var category = categoryFindService.findCategoryById(dto.getCategoryId());
        var book = dto.toEntity(account);
        book.setCategory(category);
        return bookRepository.save(book).getId();
    }

}
