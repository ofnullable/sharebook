package org.slam.publicshare.rental.exception;

import org.slam.publicshare.rental.domain.RentalStatus;

import java.util.HashMap;
import java.util.Map;

public class RentalStatusInvalidException extends RuntimeException {
    private RentalStatus oldStatus;
    private RentalStatus newStatus;
    public RentalStatusInvalidException(RentalStatus oldStatus, RentalStatus newStatus) {
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }
    public Map<String, String> getStatus() {
        var map = new HashMap<String, String>();
        map.put("oldStatus", oldStatus.toString());
        map.put("newStatus", newStatus.toString());
        return map;
    }
}
