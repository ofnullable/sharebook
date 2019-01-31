package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.mapper.book.BookSelectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookSelectService {

    private final BookSelectMapper bookSelectMapper;

    public Book selectBookDetail(Long id, String username) {
        return Optional.ofNullable(bookSelectMapper.findById(id, username))
                .orElseThrow( () -> new IllegalArgumentException("CAN NOT FOUND BOOK FOR ID : " + id) );
    }

    public List<Book> selectBookList() {
        return bookSelectMapper.findAll();
    }

    public List<Book> selectBookListByOwner(String owner) {
        return bookSelectMapper.findAllByOwner(owner);
    }

}