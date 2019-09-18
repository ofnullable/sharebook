package org.slam.publicshare.book.dto.book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.domain.BookStatus;

import javax.validation.constraints.NotBlank;

@Getter
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
    private String imageUrl;

    @Builder
    public SaveBookRequest(String title, String author, String publisher, String description, String category, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Book toEntity(Account account) {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .description(description)
                .status(BookStatus.AVAILABLE) // default status
                .owner(account)
                .imageUrl(imageUrl)
                .build();
    }

}
