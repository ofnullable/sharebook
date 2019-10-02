package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.domain.event.RentalStatusChangeEvent;
import org.slam.publicshare.rental.exception.RentalStatusInvalidException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalUpdateService implements ApplicationEventPublisherAware {

    private final RentalFindService rentalFindService;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Rental updateRental(Long id, RentalStatus rentalStatus) {
        var rental = rentalFindService.findById(id);
        switch (rentalStatus) {
            case ACCEPTED:
                rental.accept();
                break;
            case REJECTED:
                rental.reject();
                break;
            case RETURNED:
                rental.returned();
                break;
            default:
                throw new RentalStatusInvalidException(RentalStatus.NONE, rentalStatus);
        }
        eventPublisher.publishEvent(new RentalStatusChangeEvent(rental));
        return rental;
    }

}
