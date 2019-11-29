package me.ofnullable.sharebook.utils;

import me.ofnullable.sharebook.common.dto.PageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PageRequestUtils {

    public static PageRequest buildPageRequest(int size) {
        var pageRequest = new PageRequest();
        pageRequest.setPage(1);
        pageRequest.setSize(size);
        return pageRequest;
    }

    public static <T> Page<T> buildPage(List<T> list, int size) {
        var pageRequest = buildPageRequest(size);
        return new PageImpl<>(list, pageRequest.of(), list.size());
    }

    @Test
    @DisplayName("PageRequest 기본값 테스트")
    void page_request_default_value() {
        var request = buildPageRequest(10);
        request.of(); // 기본값 세팅을 위한 메서드 호출

        assertEquals(request.getPage(), Integer.valueOf(1));
        assertEquals(request.getSize(), Integer.valueOf(10));
        assertEquals(request.getDirection(), Sort.Direction.DESC);
        assertNull(request.getSortBy());

        request.setPage(0);
        request.setDirection(Sort.Direction.ASC);
        request.setSortBy("id");
        request.of();

        assertEquals(request.getPage(), Integer.valueOf(1));
        assertEquals(request.getDirection(), Sort.Direction.ASC);
        assertEquals(request.getSortBy(), "id");
    }

}
