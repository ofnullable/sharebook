package org.slam.publicshare.book.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Book book;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer sortNo;

    @Builder
    public BookImage(String imageUrl, Integer sortNo) {
        this.imageUrl = imageUrl;
        this.sortNo = sortNo;
    }

    public void addBook(Book book) {
        this.book = book;
    }

}
