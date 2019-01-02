package org.slam.service;

import org.slam.entity.book.Book;
import org.slam.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookSelectService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public Iterable<Book> selectBookList() {
		return bookRepo.findAll();
	}
	
	public Book selectBookDetail(Long id) {
		return bookRepo.findById(id)
				.orElseThrow( () -> new IllegalArgumentException("CAN NOT FOUND BOOK. ID : " + id) );
	}
	
}
