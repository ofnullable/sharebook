package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.mapper.book.BookImageMapper;
import org.slam.mapper.book.BookSaveMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookSaveService {
	
	private final BookSaveMapper bookSaveMapper;
	private final BookImageMapper bookImageMapper;
	
	@Transactional
	public void save(Book book) {
		bookSaveMapper.save(book);
		for ( int i = 0; i < book.getImages().size(); i++ ) {
			book.getImages().get(i).setBookId(book.getId());
			book.getImages().get(i).setOrdNo(i);
		}
		bookImageMapper.save(book.getImages());
	}
	
}
