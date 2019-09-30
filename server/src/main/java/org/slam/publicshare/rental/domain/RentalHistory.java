package org.slam.publicshare.rental.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.common.domain.Auditable;
import org.slam.publicshare.rental.exception.RentalAlreadyCompletionException;
import org.slam.publicshare.rental.exception.RentalNotRequestedException;
import org.slam.publicshare.rental.exception.RentalStatusEqualsException;
import org.slam.publicshare.rental.exception.RentalStatusInvalidException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalHistory extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false, updatable = false)
    private Rental rental;

    @Builder
    public RentalHistory(Rental rental, RentalStatus status) {
        verifyStatus(rental, status);
        this.rental = rental;
        this.status = status;
    }

    private void verifyStatus(Rental rental, RentalStatus status) {
        var lastStatus = getLastStatus(rental);

        if (lastStatus != null) {
            verifyWithLastStatus(lastStatus, status);
        } else {
            if (status != RentalStatus.REQUESTED) {
                throw new RentalNotRequestedException(rental.getId());
            }
        }
    }

    private RentalStatus getLastStatus(Rental rental) {
        var lastIndex = rental.getHistories().size() - 1;
        return lastIndex > -1 ? rental.getHistories().get(lastIndex).getStatus() : null;
    }

    private void verifyWithLastStatus(RentalStatus lastStatus, RentalStatus newStatus) {
        if (isStatusEquals(lastStatus, newStatus))
            throw new RentalStatusEqualsException(newStatus);
        if (isStatusInvalid(lastStatus, newStatus))
            throw new RentalStatusInvalidException(lastStatus, newStatus);
        if (isCompleted(lastStatus))
            throw new RentalAlreadyCompletionException();
    }

    private boolean isStatusInvalid(RentalStatus lastStatus, RentalStatus newStatus) {
        if (isRented(lastStatus) && newStatus == RentalStatus.REJECTED)
            return true;
        if (isRequested(lastStatus) && newStatus == RentalStatus.RETURNED)
            return true;
        return false;
    }

    private boolean isStatusEquals(RentalStatus lastStatus, RentalStatus newStatus) {
        return lastStatus == newStatus;
    }

    private boolean isRequested(RentalStatus lastStatus) {
        return lastStatus == RentalStatus.REQUESTED;
    }

    private boolean isRented(RentalStatus lastStatus) {
        return lastStatus == RentalStatus.ACCEPTED;
    }

    private boolean isCompleted(RentalStatus lastStatus) {
        return lastStatus == RentalStatus.REJECTED || lastStatus == RentalStatus.RETURNED;
    }

}
