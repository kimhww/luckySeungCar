package com.luckyRepair.seungme.luckySeungCar.main.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        return "/main/login/loginForm";
    }
}
