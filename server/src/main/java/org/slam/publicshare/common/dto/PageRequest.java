package org.slam.publicshare.common.dto;

import lombok.Getter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
public class PageRequest {

    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
    private String sortBy;
    private Sort.Direction direction;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 20;
        int MAXIMUM_SIZE = 80;
        this.size = size > MAXIMUM_SIZE ? DEFAULT_SIZE : size;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        return org.springframework.data.domain.PageRequest.of(page - 1, size, Sort.by(direction, Objects.requireNonNullElse(sortBy, "id")));
    }

}
