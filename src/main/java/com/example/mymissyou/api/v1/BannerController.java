package com.example.mymissyou.api.v1;

import com.example.mymissyou.model.Banner;
import com.example.mymissyou.service.BannerService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @GetMapping("/banner/{name}")
    @ResponseBody
    public Banner getByName(@PathVariable @NotBlank String name) {

        Banner banner = bannerService.getByName(name);
        System.out.println(name);
        System.out.println(banner);
        return banner;
    }
}
