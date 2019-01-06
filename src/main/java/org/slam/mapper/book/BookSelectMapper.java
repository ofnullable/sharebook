package org.slam.mapper.book;

import org.slam.dto.book.Book;

import java.util.List;

public interface BookSelectMapper {
	
	List<Book> findAll();
	
	List<Book> findAllById(String owner);
	
	Book findById(Long id);
	
}
