package com.luckyRepair.seungme.luckySeungCar.main.login;

import com.luckyRepair.seungme.luckySeungCar.common.LuckySeungCarConstant;
import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginDomain;
import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginUserInfoDomain;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        return "/main/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDomain domain, Model model, HttpServletRequest request) {
        String redirectUrl = "/";
        try {
            domain.setLognIpas(request.getRemoteAddr());
            domain.setLognDate(new Date());
            LoginUserInfoDomain loginUser = loginService.loginProcess(domain);
            request.getSession().setAttribute(LuckySeungCarConstant.LOGIN_USER, loginUser);

            String storedUrl = (String)request.getSession().getAttribute(LuckySeungCarConstant.REQUEST_URL);
            request.getSession().setAttribute(LuckySeungCarConstant.REQUEST_URL, null);
            if(storedUrl != null && !storedUrl.isEmpty()) return "redirect:"+storedUrl;

        } catch(LoginFailException e) {
            log.error("{}", e.getMessage());
            model.addAttribute("errMsg", messageSource.getMessage("luckySeungCar.login.failure", null, Locale.getDefault()));
            model.addAttribute("user", domain);
            return loginForm(model);
        }

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        LoginUserInfoDomain userInfo = (LoginUserInfoDomain)request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);
        log.info("Lgout - userIdnt : " + userInfo.getUserIdnt());
        request.getSession().setAttribute(LuckySeungCarConstant.LOGIN_USER, null);
        return "redirect:/";
    }
}
