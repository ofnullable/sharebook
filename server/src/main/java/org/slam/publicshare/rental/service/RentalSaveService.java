package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.event.RentalStatusChangeEvent;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalSaveService implements ApplicationEventPublisherAware {

    private final RentalRepository rentalRepository;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public Rental rentalRequest(Long bookId, Long accountId) {
        var entity = Rental.builder().bookId(bookId).accountId(accountId).build();
        entity.rental();

        var rental = rentalRepository.save(entity);
        eventPublisher.publishEvent(new RentalStatusChangeEvent(entity));
        return rental;
    }

}
