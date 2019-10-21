package org.slam.publicshare.lending.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.common.domain.Auditable;
import org.slam.publicshare.lending.exception.LendingAlreadyCompletionException;
import org.slam.publicshare.lending.exception.LendingNotRequestedException;
import org.slam.publicshare.lending.exception.LendingStatusEqualsException;
import org.slam.publicshare.lending.exception.LendingStatusInvalidException;

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

        if (lastStatus != null) {
            verifyWithLastStatus(lastStatus, status);
        } else {
            if (status != LendingStatus.REQUESTED) {
                throw new LendingNotRequestedException(lending.getId());
            }
        }
    }

    private LendingStatus getLastStatus(Lending lending) {
        var lastIndex = lending.getHistories().size() - 1;
        return lastIndex > -1 ? lending.getHistories().get(lastIndex).getStatus() : null;
    }

    private void verifyWithLastStatus(LendingStatus lastStatus, LendingStatus newStatus) {
        if (isStatusEquals(lastStatus, newStatus))
            throw new LendingStatusEqualsException(newStatus);
        if (isStatusInvalid(lastStatus, newStatus))
            throw new LendingStatusInvalidException(lastStatus, newStatus);
        if (isCompleted(lastStatus))
            throw new LendingAlreadyCompletionException();
    }

    private boolean isStatusInvalid(LendingStatus lastStatus, LendingStatus newStatus) {
        if (isRented(lastStatus) && newStatus == LendingStatus.REJECTED)
            return true;
        if (isRequested(lastStatus) && newStatus == LendingStatus.RETURNED)
            return true;
        return false;
    }

    private boolean isStatusEquals(LendingStatus lastStatus, LendingStatus newStatus) {
        return lastStatus == newStatus;
    }

    private boolean isRequested(LendingStatus lastStatus) {
        return lastStatus == LendingStatus.REQUESTED;
    }

    private boolean isRented(LendingStatus lastStatus) {
        return lastStatus == LendingStatus.ACCEPTED;
    }

    private boolean isCompleted(LendingStatus lastStatus) {
        return lastStatus == LendingStatus.REJECTED || lastStatus == LendingStatus.RETURNED;
    }

}
