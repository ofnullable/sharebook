package org.slam.publicshare.book.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slam.publicshare.book.domain.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
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
    private String category;

    @NotBlank
    @javax.validation.constraints.Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private String owner;

    @NotEmpty
    private String imageUrl;

    public SaveBookRequest(String title, String author, String publisher, String category, String description, String owner, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.description = description;
        this.owner = owner;
        this.imageUrl = imageUrl;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .description(description)
                .owner(owner)
                .imageUrl(imageUrl)
                .build();
    }

}
