package org.slam.publicshare.book.dto;

import lombok.*;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.domain.BookImage;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveBookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String publisher;

    @NotBlank
    private String description;

    @NotBlank
    @javax.validation.constraints.Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private String owner;

    @NotEmpty
    private List<BookImage> images;

    @Builder
    public SaveBookRequest(String title, String author, String publisher, String description, String owner, List<BookImage> images) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.owner = owner;
        this.images = images;
    }

    public Book toEntity() {
        var entity = Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .description(description)
                .owner(owner)
                .build();
        entity.addImages(images);
        return entity;
    }

}
