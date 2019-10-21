package org.slam.publicshare.lending.dto;

import lombok.Getter;
import org.slam.publicshare.book.dto.book.BookResponse;
import org.slam.publicshare.lending.domain.Lending;
import org.slam.publicshare.lending.domain.LendingStatus;

@Getter
public class LendingResponse {

    private Long id;
    private BookResponse book;
    private LendingStatus currentStatus;

    public LendingResponse(Lending lending) {
        this.id = lending.getId();
        this.book = new BookResponse(lending.getBook());
        this.currentStatus = lending.getCurrentStatus();
    }

}
