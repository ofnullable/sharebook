package org.slam.mapper.book;

import org.slam.dto.book.Book;

import java.util.List;

public interface BookSelectMapper {
	
	List<Book> findAll();
	
	Book findById(Long id);
	
}
