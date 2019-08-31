package org.slam.publicshare.book.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    private Book book = Book.builder()
            .title("test book")
            .author("author")
            .publisher("test")
            .description("book for test!")
            .owner("test1@asd.com")
            .build();

    private List<BookImage> images = List.of(
            BookImage.builder().imageUrl("share/book/20190830/214213721-close.png").sortNo(0).build(),
            BookImage.builder().imageUrl("share/book/20190830/214213721-next.png").sortNo(1).build()
    );

    @Test
    @DisplayName("Builder로 인스턴스 생성")
    public void book_builder_test() {
        assertEquals(book.getTitle(), "test book");
        assertEquals(book.getAuthor(), "author");
        assertEquals(book.getPublisher(), "test");
        assertEquals(book.getDescription(), "book for test!");
    }

    @Test
    @DisplayName("BookImage 추가")
    public void add_image() {
        book.addImage(images.get(0));
        assertEquals(book.getImages().size(), 1);
    }

    @Test
    @DisplayName("BookImage 리스트 추가")
    public void add_images() {
        book.addImages(images);
        assertEquals(book.getImages().size(), 2);
    }

}
