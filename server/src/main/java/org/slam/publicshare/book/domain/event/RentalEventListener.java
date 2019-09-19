package org.slam.publicshare.book.domain.event;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.book.domain.BookStatus;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.rental.domain.event.RentalEvent;
import org.slam.publicshare.rental.domain.event.ReturnEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RentalEventListener {

    private final BookFindService bookFindService;

    @Transactional
    @EventListener
    public void handleRentalEvent(RentalEvent e) {
        var target = bookFindService.findById(e.getBookId());
        target.changeStatus(BookStatus.UNAVAILABLE);
    }

    @Transactional
    @EventListener
    public void handleReturnEvent(ReturnEvent e) {
        var target = bookFindService.findById(e.getBookId());
        target.changeStatus(BookStatus.AVAILABLE);
    }

}
