package me.ofnullable.sharebook.common.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private Object key;
    private String message;
    public ResourceNotFoundException(Object key, Class clazz) {
        this.key = key;
        this.message = "Can not found " + clazz.getSimpleName() + " for key: " + key;
    }
}
