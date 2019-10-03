package org.slam.publicshare.book.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.exception.NoSuchBookException;
import org.slam.publicshare.book.repository.BookRepository;
import org.slam.publicshare.common.dto.PageRequest;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookFindService {

    private final BookRepository bookRepository;

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException(id));
    }

    public Page<Book> findAll(String searchText, PageRequest pageRequest) {
        if (StringUtils.isEmpty(searchText)) {
            return bookRepository.findAllByOrderByStatus(pageRequest.of());
        }
        return bookRepository.findALlByTitleContainingIgnoreCaseOrderByStatus(searchText, pageRequest.of());
    }

    public Page<Book> findAllByCategory(String categoryName, PageRequest pageRequest) {
        return bookRepository.findAllByCategoryNameOrderByStatus(categoryName, pageRequest.of());
    }

    public Page<Book> findAllByOwner(Long accountId, PageRequest pageRequest) {
        return bookRepository.findAllByOwnerId(accountId, pageRequest.of());
    }

    public List<Book> findAllByRentalStatus(Long accountId) {
        return bookRepository.findByRentalStatus(accountId, RentalStatus.REQUESTED);
    }

}
