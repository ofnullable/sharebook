package org.slam.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {

    @GetMapping("/")
    public String selectMyBooks() {
        return "my-page/index";
    }

    // TODO: Actions about account or else...

}