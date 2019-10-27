package me.ofnullable.sharebook.book.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.book.exception.NoSuchBookException;
import me.ofnullable.sharebook.book.repository.BookRepository;
import me.ofnullable.sharebook.common.dto.PageRequest;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public Page<Book> findAllMyBookByLendingStatus(Long accountId, LendingStatus status, PageRequest pageRequest) {
        return bookRepository.findAllByOwnerIdAndLendingsCurrentStatus(accountId, status, pageRequest.of());
    }

}
