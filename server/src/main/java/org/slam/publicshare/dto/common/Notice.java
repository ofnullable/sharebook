package org.slam.publicshare.dto.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slam.publicshare.dto.book.BookHistory;

@Getter @Setter @ToString
public class Notice {

    private String username;
    private BookHistory history;

}
