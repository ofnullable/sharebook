package org.slam.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString
public class BookImage {
	
	private Long bookId;
	private String imageUrl;
	private Integer ordNo;
	
	public BookImage(Long bookId, String imageUrl) {
		this.bookId = bookId;
		this.imageUrl = imageUrl;
	}
	
}
