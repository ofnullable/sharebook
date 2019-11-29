package me.ofnullable.sharebook.lending.exception;

import me.ofnullable.sharebook.lending.domain.LendingStatus;

import java.util.HashMap;
import java.util.Map;

public class LendingStatusInvalidException extends RuntimeException {

    private LendingStatus oldStatus;
    private LendingStatus newStatus;

    public LendingStatusInvalidException(LendingStatus oldStatus, LendingStatus newStatus) {
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
