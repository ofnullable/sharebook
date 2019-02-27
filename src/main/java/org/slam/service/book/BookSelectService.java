package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.common.Paginator;
import org.slam.mapper.book.BookSelectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookSelectService {

    private final BookSelectMapper bookSelectMapper;

    public Book findBookDetail(Long id, Authentication auth) {
        return Optional.ofNullable(bookSelectMapper.findById(id, auth))
                .orElseThrow( () -> new IllegalArgumentException("CAN NOT FOUND BOOK FOR ID : " + id) );
    }

    public List<Book> findBookList(Paginator paginator) {
        paginator.setTotal(bookSelectMapper.findTotalCount(paginator));
        return bookSelectMapper.findAll(paginator);
    }

    public Map<String, Object> findBookListByOwner(Paginator paginator) {
        var resultMap = new HashMap<String, Object>();
        paginator.setTotal(bookSelectMapper.findTotalCount(paginator));

        resultMap.put("bookList", bookSelectMapper.findAllByOwner(paginator));
        resultMap.put("paginator", paginator);

        return resultMap;
    }

}