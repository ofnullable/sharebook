package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.BookCategory;
import org.slam.publicshare.book.exception.NoSuchBookCategoryException;
import org.slam.publicshare.book.repository.BookCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryFindService {

    private final BookCategoryRepository bookCategoryRepository;

    public List<BookCategory> findAllByDisplay(boolean display) {
        return bookCategoryRepository.findAllByDisplayOrderByName(display);
    }

    public BookCategory findByName(String name) {
        return bookCategoryRepository.findByNameAndDisplayIsTrue(name)
                .orElseThrow(() -> new NoSuchBookCategoryException(name));
    }

}
