package com.luckyRepair.seungme.luckySeungCar.user.menu.notice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/menu/notice")
public class noticeController {
    @GetMapping
    public String main() {
        return "menu/notice/noticeIndex";
    }
}
