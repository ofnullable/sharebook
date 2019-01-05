package org.slam.mapper.book;

import org.slam.dto.book.Book;

import java.util.List;

public interface BookSaveMapper {
	
	void save(Book book);
	
	void saveAll(List<Book> books);
	
}
