package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.exception.NoSuchBookException;
import org.slam.publicshare.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookFindService {

    private final BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException(id));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
