package org.slam.dto.common;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class Paginator {

    private class PageConstants {
        private static final int ITEM_COUNT = 6;
        private static final int PAGE_COUNT = 5;
    }

    private Integer page;
    private Integer total;
    private Integer start;
    private Integer end;
    private Integer prev;
    private Integer next;

    // for search feature
    private String searchText;
    private String username;

    public Integer getSelectCount() {
        return PageConstants.ITEM_COUNT * PageConstants.PAGE_COUNT;
    }

    public Integer getJumpCount() {
        return this.page > 1 ? ((int) Math.floor((double) page / PageConstants.ITEM_COUNT)) * getSelectCount() :
                ((int) Math.floor((double) page / PageConstants.ITEM_COUNT)) * getSelectCount();
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText.toLowerCase();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setTotal(Integer total) {
        this.total = total;
        if (this.page != null) {
            setup();
        } else {
            this.page = 1;
            setup();
        }
    }

    private void setup() {
        int totalEnd = (int) Math.ceil((double) total / PageConstants.ITEM_COUNT);
        int pageEnd = ((int) Math.ceil((double) page / PageConstants.PAGE_COUNT)) * PageConstants.PAGE_COUNT;

        this.end = totalEnd < pageEnd ? totalEnd : pageEnd;
        this.start = pageEnd - (PageConstants.PAGE_COUNT - 1);
        this.prev = start == 1 ? null : start - 1;
        this.next = totalEnd < pageEnd ? null : end + 1;
    }

}
