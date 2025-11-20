package com.luckyRepair.seungme.luckySeungCar.user.menu.about;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/menu/about")
public class aboutController {
    @GetMapping
    public String main() {
        return "menu/about/aboutIndex";
    }
}
