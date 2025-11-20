package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/menu/lsc/review")
public class ReviewController {
    @GetMapping
    public String main() {
        return "menu/lsc/review/reviewIndex";
    }
}
