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

    @Transactional
    public Book save(SaveBookRequest dto) {
        return bookRepository.save(dto.toEntity());
    }

}
