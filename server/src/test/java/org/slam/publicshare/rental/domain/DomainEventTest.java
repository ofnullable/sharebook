package org.slam.publicshare.rental.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.rental.domain.event.RentalStatusChangeEvent;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.slam.publicshare.rental.service.RentalFindService;
import org.slam.publicshare.rental.service.RentalSaveService;
import org.slam.publicshare.rental.service.RentalUpdateService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.slam.publicshare.rental.utils.RentalUtils.buildAcceptedRental;
import static org.slam.publicshare.rental.utils.RentalUtils.buildRequestedRental;

@ExtendWith(SpringExtension.class)
public class DomainEventTest {

    @InjectMocks
    private RentalSaveService rentalSaveService;

    @InjectMocks
    private RentalUpdateService rentalUpdateService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private RentalFindService rentalFindService;

    @BeforeEach
    public void setup() {
        rentalSaveService.setApplicationEventPublisher(eventPublisher);
        rentalUpdateService.setApplicationEventPublisher(eventPublisher);
    }

    @Test
    @DisplayName("대여 기록이 존재하지 않는 경우 이벤트 생성 실패")
    public void publish_event_failure() {
        var rental = Rental.builder().bookId(1L).accountId(1L).build();

        assertThrows(IllegalArgumentException.class, () -> new RentalStatusChangeEvent(rental));
    }

    @Test
    @DisplayName("대여요청 이벤트")
    public void rental_request() {
        var rental = buildRequestedRental();

        given(rentalRepository.save(any(Rental.class)))
                .willReturn(rental);

        rentalSaveService.rentalRequest(1L, 1L);

        verify(eventPublisher, atLeastOnce()).publishEvent(any(RentalStatusChangeEvent.class));
    }


    @Test
    @DisplayName("대여거절 이벤트")
    public void update_rental_to_rejected() {
        var rental = buildRequestedRental();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        rentalUpdateService.updateRental(1L, RentalStatus.REJECTED);

        verify(eventPublisher, atLeastOnce()).publishEvent(any(RentalStatusChangeEvent.class));
    }

    @Test
    @DisplayName("대여거절 이벤트")
    public void update_rental_to_returned() {
        var rental = buildAcceptedRental();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        rentalUpdateService.updateRental(1L, RentalStatus.RETURNED);

        verify(eventPublisher, atLeastOnce()).publishEvent(any(RentalStatusChangeEvent.class));
    }

}
