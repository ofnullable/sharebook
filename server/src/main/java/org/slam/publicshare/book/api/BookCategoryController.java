package org.slam.publicshare.book.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.dto.BookCategoryResponse;
import org.slam.publicshare.book.service.BookCategoryFindService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookCategoryController {

    private final BookCategoryFindService bookCategoryFindService;

    @GetMapping("/categories")
    public List<BookCategoryResponse> findAll() {
        var display = true;
        return bookCategoryFindService.findAllByDisplay(display).stream()
                .map(BookCategoryResponse::new)
                .collect(Collectors.toList());
    }

}
