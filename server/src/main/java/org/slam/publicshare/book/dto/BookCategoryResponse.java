package org.slam.publicshare.book.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.book.domain.BookCategory;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookCategoryResponse {

    private Long id;
    private String name;

    public BookCategoryResponse(BookCategory category) {
        this.id = category.getId();
        this.name = category.getName();
    }

}
