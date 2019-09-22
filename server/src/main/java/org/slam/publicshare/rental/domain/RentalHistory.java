package org.slam.publicshare.rental.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.common.domain.Auditable;
import org.slam.publicshare.rental.exception.HistoryStatusEqualsException;
import org.slam.publicshare.rental.exception.RentalAlreadyCompletionException;
import org.slam.publicshare.rental.exception.RentalAlreadyStartedException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalHistory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
            verifyWithLastStatus(status, lastStatus);
        }
    }

    private RentalStatus getLastStatus(Rental rental) {
        var lastIndex = rental.getHistories().size() - 1;
        return lastIndex > -1 ? rental.getHistories().get(lastIndex).getStatus() : null;
    }

    private void verifyWithLastStatus(RentalStatus newStatus, RentalStatus lastStatus) {
        if (lastStatus == newStatus)
            throw new HistoryStatusEqualsException(newStatus);
        if (isCompleted(lastStatus))
            throw new RentalAlreadyCompletionException();
        if (isRented(lastStatus) && newStatus == RentalStatus.REJECTED)
            throw new RentalAlreadyStartedException();
    }

    private boolean isCompleted(RentalStatus lastStatus) {
        return lastStatus == RentalStatus.REJECTED || lastStatus == RentalStatus.RETURNED;
    }

    private boolean isRented(RentalStatus lastStatus) {
        return lastStatus == RentalStatus.ACCEPTED;
    }

}
