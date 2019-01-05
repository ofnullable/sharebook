package org.slam.service;

import org.slam.dto.book.Book;
import org.slam.mapper.book.BookImageMapper;
import org.slam.mapper.book.BookSaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookSaveService {
	
	@Autowired
	private BookSaveMapper bookSaveMapper;
	@Autowired
	private BookImageMapper bookImageMapper;
	
	@Transactional
	public void save(Book book) {
		bookSaveMapper.save(book);
		bookImageMapper.save(book);
	}
	
}
