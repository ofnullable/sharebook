package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.dto.SaveBookRequest;
import org.slam.publicshare.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookSaveService {

    private final BookRepository bookRepository;
    private final BookCategoryFindService bookCategoryFindService;

    @Transactional
    public Book save(SaveBookRequest dto) {
        var book = dto.toEntity();
        var category = bookCategoryFindService.findByName(dto.getCategory());
        book.setCategory(category);
        return bookRepository.save(dto.toEntity());
    }

}
