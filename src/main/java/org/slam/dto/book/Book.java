package org.slam.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class Book {
	
	private Long id;
	private String title;
	private String author;
	private String description;
	private String owner;
	private LocalDateTime modifiedAt;
	private LocalDateTime createdAt;
	
	private List<String> images;
	
	public Book(String title, String author, List<String> images) {
		this.title = title;
		this.author = author;
		this.images = images;
	}
	
}
