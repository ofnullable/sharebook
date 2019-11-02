package me.ofnullable.sharebook.book.dto.book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.book.domain.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Getter
    @NotNull
    private Long categoryId;

    @NotBlank
    private String imageUrl;

    @Builder
    public SaveBookRequest(String title, String author, String publisher, String description, Long categoryId, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    public Book toEntity(Account account) {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .description(description)
                .owner(account)
                .imageUrl(imageUrl)
                .build();
    }

}
