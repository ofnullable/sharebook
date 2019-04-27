package org.slam.dto.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slam.dto.book.BookHistory;

@Getter @Setter @ToString
public class Notice {

    private String username;
    private BookHistory history;

}
