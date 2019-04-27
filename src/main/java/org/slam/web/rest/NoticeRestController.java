package org.slam.web.rest;

import lombok.AllArgsConstructor;
import org.slam.dto.book.BookHistory;
import org.slam.service.notice.NoticeService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notice")
public class NoticeRestController {

    private final NoticeService noticeService;

    @GetMapping("/{username}")
    public List<BookHistory> getNotices(Authentication auth) {
        return noticeService.findByUsername(auth.getName());
    }

    @PatchMapping("/{historyId}")
    public int updateToChecked(@PathVariable Long historyId, Authentication auth) {
        return noticeService.updateToChecked(historyId, auth.getName());
    }

}
