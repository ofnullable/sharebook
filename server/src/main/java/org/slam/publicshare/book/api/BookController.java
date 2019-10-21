package org.slam.publicshare.book.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.book.dto.book.BookResponse;
import org.slam.publicshare.book.dto.book.SaveBookRequest;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.book.service.BookSaveService;
import org.slam.publicshare.common.dto.PageRequest;
import org.slam.publicshare.lending.domain.LendingStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookSaveService bookSaveService;
    private final BookFindService bookFindService;

    @GetMapping("/book/{id}")
    public BookResponse findBookById(@PathVariable Long id) {
        return new BookResponse(bookFindService.findById(id));
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse saveBook(
            @AuthenticationPrincipal(expression = "account") Account account,
            @RequestBody @Valid SaveBookRequest dto) {
        return new BookResponse(bookSaveService.save(dto, account));
    }

    @GetMapping("/books")
    public Page<BookResponse> findAllBook(
            @Nullable final String searchText,
            @Valid PageRequest pageRequest) {
        return bookFindService.findAll(searchText, pageRequest)
                .map(BookResponse::new);
    }

    @GetMapping("/books/category/{category}")
    public Page<BookResponse> findAllBookByCategory(
            @PathVariable String category,
            @Valid PageRequest pageRequest) {
        return bookFindService.findAllByCategory(category, pageRequest)
                .map(BookResponse::new);
    }

    @GetMapping("/account/{accountId}/books")
    public Page<BookResponse> findAllBookByOwner(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable Long accountId,
            @Valid PageRequest pageRequest) {
        if (accountId == 0) {
            return bookFindService.findAllByOwner(account.getId(), pageRequest)
                    .map(BookResponse::new);
        }
        return bookFindService.findAllByOwner(accountId, pageRequest)
                .map(BookResponse::new);
    }

    @GetMapping("/account/books/lending/{status}")
    public Page<BookResponse> findAllMyBookByLendingStatus(
            @AuthenticationPrincipal(expression = "account") Account account,
            @PathVariable LendingStatus status,
            @Valid PageRequest pageRequest) {
        return bookFindService.findAllMyBookByLendingStatus(account.getId(), status, pageRequest)
                .map(BookResponse::new);
    }

}
