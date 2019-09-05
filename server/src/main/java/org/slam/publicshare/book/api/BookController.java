package org.slam.publicshare.book.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.dto.BookResponse;
import org.slam.publicshare.book.dto.SaveBookRequest;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.book.service.BookSaveService;
import org.slam.publicshare.common.dto.PageableRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final BookSaveService bookSaveService;
    private final BookFindService bookFindService;

    @GetMapping("/books")
    public Page<BookResponse> findAll(@Nullable final String searchText, @Valid final PageableRequest pageableRequest) {
        return bookFindService.findAll(searchText, pageableRequest)
                .map(BookResponse::new);
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse save(@RequestBody @Valid SaveBookRequest dto) {
        log.info("dto: {}", dto);
        return new BookResponse(bookSaveService.save(dto));
    }

    @GetMapping("/book/{id}")
    public BookResponse findById(@PathVariable Long id) {
        return new BookResponse(bookFindService.findById(id));
    }

}
