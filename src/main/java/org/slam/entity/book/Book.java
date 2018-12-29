package org.slam.entity.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.slam.entity.account.Account;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@NoArgsConstructor
public class Book {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String author;
	private String description;
	
	@LastModifiedDate
	private LocalDateTime modifiedAt;
	@CreatedDate
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "owner", referencedColumnName = "username")
	private Account owner;
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@ElementCollection(targetClass = String.class)
	@JoinTable(name = "book_image", joinColumns = @JoinColumn(name = "book_id"))
	private List<String> images;
	
	public Book(String title, String author, List<String> images) {
		this.title = title;
		this.author = author;
		this.images = images;
	}
	
}
