package org.slam.publicshare.interceptor;

import lombok.AllArgsConstructor;
import org.slam.publicshare.service.notice.NoticeService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class NoticeInterceptor extends HandlerInterceptorAdapter {

    private final NoticeService noticeService;

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
        var session = req.getSession(false);

//        if (session != null && session.getAttribute("auth") != null && isNoticeExist(modelAndView)) {
//            var noticeCount = noticeService.findTotalCount( (AccountDto) session.getAttribute("auth") );
//            modelAndView.getModel().put("notices", noticeCount);
//        }

        super.postHandle(req, res, handler, modelAndView);
    }

    private boolean isNoticeExist(ModelAndView mav) {
        if (mav == null) return false;
        return mav.getModel().get("notices") == null;
    }

}
