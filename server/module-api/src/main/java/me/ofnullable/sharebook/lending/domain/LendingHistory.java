package me.ofnullable.sharebook.lending.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.common.domain.Auditable;
import me.ofnullable.sharebook.lending.exception.LendingAlreadyCompletionException;
import me.ofnullable.sharebook.lending.exception.LendingNotRequestedException;
import me.ofnullable.sharebook.lending.exception.LendingStatusEqualsException;
import me.ofnullable.sharebook.lending.exception.LendingStatusInvalidException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LendingHistory extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LendingStatus status;

    @ManyToOne(optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Lending lending;

    @Builder
    public LendingHistory(Lending lending, LendingStatus status) {
        verifyStatus(lending, status);
        this.lending = lending;
        this.status = status;
    }

    private void verifyStatus(Lending lending, LendingStatus status) {
        var lastStatus = getLastStatus(lending);

        if (lastStatus == null) {
            if (status != LendingStatus.REQUESTED) {
                throw new LendingNotRequestedException(lending.getId());
            }
        } else {
            verifyWithLastStatus(lastStatus, status);
        }
    }

    private LendingStatus getLastStatus(Lending lending) {
        var lastIndex = lending.getHistories().size() - 1;
        return lastIndex > -1 ? lending.getHistories().get(lastIndex).getStatus() : null;
    }

    private void verifyWithLastStatus(LendingStatus lastStatus, LendingStatus newStatus) {
        if (isCompleted(lastStatus))
            throw new LendingAlreadyCompletionException();
        if (isStatusEquals(lastStatus, newStatus))
            throw new LendingStatusEqualsException(newStatus);
        if (isInvalidStatus(lastStatus, newStatus))
            throw new LendingStatusInvalidException(lastStatus, newStatus);
    }

    private boolean isStatusEquals(LendingStatus lastStatus, LendingStatus newStatus) {
        return lastStatus == newStatus;
    }

    /*
     * Next status can be:
     *   origin         next status
     * REQUESTED -> CANCELED || ACCEPTED || REJECTED
     * ACCEPTED  -> RETURNED
     * CANCELED }
     * REJECTED }-> X
     * RETURNED }
     */
    private boolean isInvalidStatus(LendingStatus lastStatus, LendingStatus newStatus) {
        if (isRequested(lastStatus) && newStatus == LendingStatus.RETURNED)
            return true;
        if (isAccepted(lastStatus) && newStatus != LendingStatus.RETURNED)
            return true;
        return false;
    }

    private boolean isRequested(LendingStatus lastStatus) {
        return lastStatus == LendingStatus.REQUESTED;
    }

    private boolean isAccepted(LendingStatus lastStatus) {
        return lastStatus == LendingStatus.ACCEPTED;
    }

    private boolean isCompleted(LendingStatus lastStatus) {
        return lastStatus == LendingStatus.REJECTED || lastStatus == LendingStatus.RETURNED || lastStatus == LendingStatus.CANCELED;
    }

}
