package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.review;

import com.luckyRepair.seungme.luckySeungCar.common.LuckySeungCarConstant;
import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginUserInfoDomain;
import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.ReserveService;
import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain.ReserveDetailDomain;
import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain.ReserveDomain;
import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.review.domain.ReviewDomain;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/menu/lsc/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @Autowired
    ReserveService reserveService;

    @GetMapping
    public String main() {
        return "menu/lsc/review/reviewIndex";
    }

    @GetMapping("/reviewCheckReserve")
    public String reviewCheckReserve(ReviewDomain reviewDomain, HttpServletRequest request, Model model) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);

        reviewDomain.setUserIdnt(loginInfo.getUserIdnt());
        List<ReviewDomain> myReviewList = reviewService.selectMyReviewList(reviewDomain);
        System.out.println(">>>>myReviewList : " + myReviewList);
        model.addAttribute("myReviewList", myReviewList);

        return "menu/lsc/review/reviewCheckReserve";
    }

    @GetMapping("/reviewForm")
    public String reviewForm(@ModelAttribute ReviewDomain reviewDomain, HttpServletRequest request, Model model) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);

        reviewDomain.setUserIdnt(loginInfo.getUserIdnt());

        ReviewDomain myReviewListDetail = reviewService.selectMyReviewListDetail(reviewDomain);
        System.out.println(">>>>myReviewListDetail : " + myReviewListDetail);
        model.addAttribute("myReviewListDetail", myReviewListDetail);

        return "menu/lsc/review/reviewForm";
    }

    @GetMapping("/reviewList")
    public String reviewList(ReviewDomain reviewDomain, HttpServletRequest request, Model model) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);

        reviewDomain.setUserIdnt(loginInfo.getUserIdnt());
        model.addAttribute("reviewList", reviewService.selectMyWrittenReviewList(reviewDomain));

        return "menu/lsc/review/reviewList";
    }

    /*@PostMapping("/saveReview")
    public String saveReview(ReviewDomain reviewDomain, HttpServletRequest request) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);

        ReviewDomain selectMaxSqnc = reviewService.selectMaxSqnc();
        Integer maxSqnc;
        if(selectMaxSqnc == null){
            maxSqnc = 1;
        } else {
            maxSqnc = selectMaxSqnc.getMaxResvSqnc() + 1;
        }

        reviewDomain.setRevwSqnc(maxSqnc);
        reviewDomain.setDelcYorn("N");
        //resvStat[1: 접수, 2: 승인, 3: 완료, 4: 취소]

        reviewDomain.setUserIdnt(loginInfo.getUserIdnt());
        reviewDomain.setWorkIdnt(loginInfo.getUserIdnt());
        reviewDomain.setWorkDate(new Date());
        reviewDomain.setWorkIpas(request.getRemoteAddr());

        System.out.println(">>>>REVIEWDOMAIN : " + reviewDomain);

        reviewService.insertReview(reviewDomain);

        return "menu/lsc/review/reviewIndex";
    }*/
    @PostMapping("/saveReview")
    public String saveReview(ReviewDomain reviewDomain, HttpServletRequest request) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);
        reviewDomain.setUserIdnt(loginInfo.getUserIdnt());

        // hidden 값 변조 방지: 내 예약이 맞는지 확인
        if (reviewService.selectMyReviewListDetail(reviewDomain) == null) {
            return "redirect:/menu/lsc/review";
        }

        reviewDomain.setRevwSqnc(reviewService.selectNextRevwSqnc());
        reviewDomain.setDelcYorn("N");
        reviewDomain.setWorkIdnt(loginInfo.getUserIdnt());
        reviewDomain.setWorkDate(new Date());
        reviewDomain.setWorkIpas(request.getRemoteAddr());

        reviewService.insertReview(reviewDomain);

        return "redirect:/menu/lsc/review/reviewList";
    }

    @GetMapping("/reviewDetail")
    public String reviewDetail(ReviewDomain reviewDomain, HttpServletRequest request, Model model) {
        LoginUserInfoDomain loginInfo = (LoginUserInfoDomain) request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);
        reviewDomain.setUserIdnt(loginInfo.getUserIdnt());

        ReviewDomain myReviewDetail = reviewService.selectMyWrittenReviewDetail(reviewDomain);
        if (myReviewDetail == null) {
            return "redirect:/menu/lsc/review/reviewList";
        }
        model.addAttribute("myReviewDetail", myReviewDetail);

        return "menu/lsc/review/reviewDetail";
    }
}
