package com.luckyRepair.seungme.luckySeungCar.main.user;

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
    public String joinUser(@ModelAttribute UserDomain userDomain, HttpServletRequest request, Model model) {
        String clientIp = request.getRemoteAddr();

        userDomain.setWorkIdnt(userDomain.getUserIdnt());
        userDomain.setWorkDate(new Date());
        userDomain.setUpdtDate(new Date());
        userDomain.setWorkIpas(clientIp);

        System.out.println("domain: " + userDomain);

        userService.insertUser(userDomain);

        model.addAttribute("message", "회원가입이 완료되었습니다!");
        //return "redirect:/user/joinComplete"; // 가입 완료 페이지
        return "/main/login/loginForm";
    }
}
