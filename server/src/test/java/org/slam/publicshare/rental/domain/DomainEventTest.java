package org.slam.publicshare.rental.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slam.publicshare.book.service.event.RentalEventListener;
import org.slam.publicshare.rental.domain.event.RentalStatusChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.slam.publicshare.rental.utils.RentalUtils.buildAcceptedRental;
import static org.slam.publicshare.rental.utils.RentalUtils.buildRequestedRental;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DomainEventTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @MockBean
    private RentalEventListener eventListener;

    @Test
    @DisplayName("대여 기록이 존재하지 않는 경우 이벤트 생성 실패")
    public void publish_event_failure() {
        var rental = Rental.builder().bookId(1L).accountId(1L).build();

        assertThrows(IllegalArgumentException.class, () -> new RentalStatusChangeEvent(rental));
    }

    @Test
    @DisplayName("대여요청시 이벤트 발행")
    public void rental_request() {
        var rentalEvent = new RentalStatusChangeEvent(buildRequestedRental());
        eventPublisher.publishEvent(rentalEvent);
        verify(eventListener, atLeastOnce()).handleRentalEvent(any(RentalStatusChangeEvent.class));
    }

    @Test
    @DisplayName("대여요청 거절시 이벤트 발행")
    public void reject() {
        var rental = buildRequestedRental();
        rental.reject();
        var rentalEvent = new RentalStatusChangeEvent(rental);
        eventPublisher.publishEvent(rentalEvent);
        verify(eventListener, atLeastOnce()).handleRentalEvent(any(RentalStatusChangeEvent.class));
    }

    @Test
    @DisplayName("반납요청시 이벤트 발행")
    public void returned() {
        var rental = buildAcceptedRental();
        rental.returned();
        var rentalEvent = new RentalStatusChangeEvent(rental);
        eventPublisher.publishEvent(rentalEvent);
        verify(eventListener, atLeastOnce()).handleRentalEvent(any(RentalStatusChangeEvent.class));
    }

}
