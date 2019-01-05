package org.slam.service.book;

import org.slam.dto.book.Book;
import org.slam.mapper.book.BookSelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookSelectService {
	
	@Autowired
	private BookSelectMapper bookSelectMapper;
	
	public Iterable<Book> selectBookList() {
		return bookSelectMapper.findAll();
	}
	
	public Book selectBookDetail(Long id) {
		return Optional.ofNullable(bookSelectMapper.findById(id))
				.orElseThrow( () -> new IllegalArgumentException("CAN NOT FOUND BOOK FOR ID : " + id) );
	}
	
}
