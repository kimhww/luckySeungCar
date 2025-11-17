package com.luckyRepair.seungme.luckySeungCar.main.user;

import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginDomain;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/agreement")
    public String joginAgree() {
        return "/main/user/agreement";
    }

    @GetMapping("/joinForm")
    public String check() {
        return "/main/user/joinForm";

    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserDomain domain, Model model, HttpServletRequest request) {
        domain.setWorkDate(new Date());
        domain.setWorkIpas(request.getRemoteAddr());

        System.out.println(">>>>domainJoin: " + domain);
        userService.insert(domain);

        LoginDomain loginDomain = LoginDomain.builder().userIdnt(domain.getUserIdnt()).build();
        model.addAttribute("isJoin", true);
        model.addAttribute("user", loginDomain);
        return "/main/login/loginForm";
    }
}
