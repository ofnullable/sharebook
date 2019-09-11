package org.slam.publicshare.common.utils;

import org.slam.publicshare.common.dto.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class PageRequestUtils {

    public static PageRequest buildPageable(int size) {
        var pageRequest = new PageRequest();
        pageRequest.setPage(0);
        pageRequest.setSize(size);
        return pageRequest;
    }

    public static <T> Page<T> buildPage(List<T> list, int size) {
        var pageRequest = buildPageable(size);
        return new PageImpl<>(list, pageRequest.of(), list.size());
    }

}
