package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.review;

import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain.ReserveDetailDomain;
import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain.ReserveDomain;
import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.review.domain.ReviewDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Transactional
    public void insertReview(ReviewDomain domain) {
        reviewDao.insertReview(domain);
    }

    public ReviewDomain selectMaxSqnc() {
        return reviewDao.selectMaxSqnc();
    }

    public List<ReviewDomain> selectMyReviewList(ReviewDomain reviewDomain) {
        return reviewDao.selectMyReviewList(reviewDomain);
    }

    public ReviewDomain selectMyReviewListDetail(ReviewDomain reviewDomain){
        return reviewDao.selectMyReviewListDetail(reviewDomain);
    }
}
