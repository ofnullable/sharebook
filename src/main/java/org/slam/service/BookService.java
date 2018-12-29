package org.slam.service;

import lombok.extern.log4j.Log4j2;
import org.slam.entity.book.Book;
import org.slam.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Log4j2
@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public Iterable<Book> getBookList() {
		return bookRepo.findAll();
	}
	
	@PostConstruct
	private void init() {
		bookRepo.findById(1L).ifPresentOrElse(
				b -> log.info("FIRST BOOK : {}", b),
				() -> {
					var book1 = new Book( "TITLE", "AUTHOR", Arrays.asList( "asd", "asd", "asd" ) );
					var book2 = new Book( "TITLE", "AUTHOR", Arrays.asList( "asd", "asd", "asd" ) );
					var book3 = new Book( "TITLE", "AUTHOR", Arrays.asList( "asd", "asd", "asd" ) );
					log.info("SAVE DEFAULT BOOK : {}", bookRepo.saveAll(Arrays.asList(book1, book2, book3)));
				}
		);
	}
	
}
