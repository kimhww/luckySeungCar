package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.review;

import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.review.domain.ReviewDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewDao {
    void insertReview(ReviewDomain reviewDomain);

    public ReviewDomain selectMaxSqnc();

    List<ReviewDomain> selectMyReviewList(ReviewDomain reviewDomain);

    public ReviewDomain selectMyReviewListDetail(ReviewDomain reviewDomain);
}
