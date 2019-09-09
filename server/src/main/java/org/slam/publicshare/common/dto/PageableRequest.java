package org.slam.publicshare.common.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
public class PageableRequest {

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
        int DEFAULT_LIMIT = 10;
        int MAXIMUM_LIMIT = 50;
        this.size = size <= MAXIMUM_LIMIT ? size : DEFAULT_LIMIT;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public PageRequest of() {
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        return PageRequest.of(page - 1, size, Sort.by(direction, Objects.requireNonNullElse(sortBy, "id")));
    }

}
