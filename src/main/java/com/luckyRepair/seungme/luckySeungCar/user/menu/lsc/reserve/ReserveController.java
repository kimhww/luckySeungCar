package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve;

import com.luckyRepair.seungme.luckySeungCar.common.LuckySeungCarConstant;
import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginUserInfoDomain;
import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain.ReserveDomain;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/menu/lsc/reserve")
public class ReserveController {
    @Autowired
    ReserveService reserveService;

    @GetMapping
    public String main() {
        return "menu/lsc/reserve/reserveIndex";
    }

    @GetMapping("/reserveForm")
    public String reserveForm() {
        return "menu/lsc/reserve/reserveForm";
    }

    @GetMapping("/checkReserve")
    public String checkReserve(ReserveDomain reserveDomain, HttpServletRequest request, Model model) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);

        reserveDomain.setUserIdnt(loginInfo.getUserIdnt());
        //ReserveDomain myReserveList = reserveService.selectMyReserveList(reserveDomain);
        List<ReserveDomain> myReserveList = reserveService.selectMyReserveList(reserveDomain);
        System.out.println(">>>>myReserveList : " + myReserveList);
        model.addAttribute("myReserveList", myReserveList);

        return "menu/lsc/reserve/checkReserve";
    }

    @PostMapping("/saveReserve")
    public String saveReserve(ReserveDomain reserveDomain, HttpServletRequest request) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);

        ReserveDomain selectMaxSqnc = reserveService.selectMaxSqnc();
        Integer maxSqnc;
        if(selectMaxSqnc == null){
            maxSqnc = 1;
        } else {
            maxSqnc = selectMaxSqnc.getMaxResvSqnc() + 1;
        }

        reserveDomain.setResvSqnc(maxSqnc);
        reserveDomain.setDelcYorn("N");
        reserveDomain.setResvStat("1");
        //resvStat[1: 접수, 2: 승인, 3: 완료, 4: 취소]

        reserveDomain.setUserIdnt(loginInfo.getUserIdnt());
        reserveDomain.setWorkIdnt(loginInfo.getUserIdnt());
        reserveDomain.setWorkDate(new Date());
        reserveDomain.setWorkIpas(request.getRemoteAddr());

        System.out.println(">>>>RESERVEDOMAIN : " + reserveDomain);

        reserveService.insertReserve(reserveDomain);

        return "menu/lsc/reserve/reserveIndex";
    }
}
