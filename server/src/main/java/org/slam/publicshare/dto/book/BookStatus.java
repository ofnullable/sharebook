package org.slam.publicshare.dto.book;

public enum BookStatus {
    AVAILABLE,
    WAIT_FOR_RESPONSE,
    ON_LOAN,
    ON_RESERVED,
    CANCELED,
    REJECTED,
    RETURN_REQUEST,
    RETURNED,
    LOAN_OVERDUE
}
