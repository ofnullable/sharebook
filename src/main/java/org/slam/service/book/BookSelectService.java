package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.mapper.book.BookSelectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookSelectService {

    private final BookSelectMapper bookSelectMapper;

    public List<Book> selectBookList() {
        return bookSelectMapper.findAll();
    }

    public List<Book> selectBookList(String owner) {
        return bookSelectMapper.findAllById(owner);
    }

    public Book selectBookDetail(Long id) {
        return Optional.ofNullable(bookSelectMapper.findById(id))
                .orElseThrow( () -> new IllegalArgumentException("CAN NOT FOUND BOOK FOR ID : " + id) );
    }

}