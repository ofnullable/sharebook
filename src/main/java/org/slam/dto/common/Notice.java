package org.slam.dto.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Notice {

    private String subject; // 주제
    private Integer count;  // 합계
    private String content; // 알림내용

}
