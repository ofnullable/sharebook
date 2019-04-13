package org.slam.dto.book;

import lombok.*;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class BookImage {

    private Long    bookId;
    private String  imageUrl;
    private Integer ordNo;

}