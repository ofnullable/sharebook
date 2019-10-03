package org.slam.publicshare.book.service.event;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.BookStatus;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.domain.event.RentalStatusChangeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RentalEventListener {

    private final BookFindService bookFindService;

    @Async
    @Transactional
    @EventListener
    public void handleRentalEvent(RentalStatusChangeEvent event) {
        var target = bookFindService.findById(event.getBookId());
        if (event.getLastStatus() == RentalStatus.REQUESTED)
            target.changeStatus(BookStatus.UNAVAILABLE);
        else
            target.changeStatus(BookStatus.AVAILABLE);
    }

}
