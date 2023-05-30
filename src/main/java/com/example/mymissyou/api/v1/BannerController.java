package com.example.mymissyou.api.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class BannerController {
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "hello, 2221111sss1";
    }
}
