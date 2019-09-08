package org.slam.publicshare.book.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    private Book book = Book.builder()
            .title("test book")
            .author("author")
            .publisher("test")
            .description("book for test!")
            .owner("test1@asd.com")
            .imageUrl("")
            .build();

    private BookCategory category = BookCategory.of("운영체제");

    @Test
    @DisplayName("Builder로 인스턴스 생성")
    public void book_builder_test() {
        assertEquals(book.getTitle(), "test book");
        assertEquals(book.getAuthor(), "author");
        assertEquals(book.getPublisher(), "test");
        assertEquals(book.getDescription(), "book for test!");
    }

    @Test
    @DisplayName("도서 카테고리 추가")
    public void book_set_category_test() {
        book.setCategory(category);
        assertEquals(book.getCategory().getName(), category.getName());
    }

}
