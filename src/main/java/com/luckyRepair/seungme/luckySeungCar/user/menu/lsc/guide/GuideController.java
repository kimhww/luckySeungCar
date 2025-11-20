package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.guide;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/menu/lsc/guide")
public class GuideController {
    @GetMapping
    public String main() {
        return "menu/lsc/guide/guideIndex";
    }
}
