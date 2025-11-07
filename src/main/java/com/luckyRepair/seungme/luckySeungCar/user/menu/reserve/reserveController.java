package com.luckyRepair.seungme.luckySeungCar.user.menu.reserve;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/menu/reserve")
public class reserveController {
    @GetMapping
    public String main() {
        return "menu/reserve/reserveIndex";
    }
}
