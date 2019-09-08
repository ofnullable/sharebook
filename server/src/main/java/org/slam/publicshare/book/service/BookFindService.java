package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.exception.NoSuchBookException;
import org.slam.publicshare.book.repository.BookRepository;
import org.slam.publicshare.common.dto.PageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFindService {

    private final BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException(id));
    }

    public Page<Book> findAll(String searchText, PageableRequest pageableRequest) {
        if (searchText == null) {
            return bookRepository.findAll(pageableRequest.of());
        }
        return bookRepository.findByTitleContainingIgnoreCase(searchText, pageableRequest.of());
    }

}
