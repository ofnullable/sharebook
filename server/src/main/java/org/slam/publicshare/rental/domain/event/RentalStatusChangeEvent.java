package org.slam.publicshare.rental.domain.event;

import lombok.Getter;
import org.slam.publicshare.rental.domain.Rental;
import org.springframework.context.ApplicationEvent;

@Getter
public class RentalStatusChangeEvent extends ApplicationEvent {

    private Rental rental;

    public RentalStatusChangeEvent(Object source) {
        super(source);
        this.rental = (Rental) source;
    }

}
