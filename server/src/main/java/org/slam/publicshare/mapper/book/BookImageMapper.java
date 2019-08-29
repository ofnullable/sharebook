package org.slam.publicshare.mapper.book;

import org.slam.publicshare.dto.book.BookImage;

import java.util.List;

public interface BookImageMapper {

    void save(List<BookImage> bookImages);

}