package com.luckyRepair.seungme.luckySeungCar.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class  MainController {

    @GetMapping("/")
    public String main() {
        log.info("메인 페이지 호출됨");
        return "main/index";
    }

    @GetMapping("/main/index")
    public String index() {
        log.info("index 페이지 호출됨");
        return "main/index";
    }
}
