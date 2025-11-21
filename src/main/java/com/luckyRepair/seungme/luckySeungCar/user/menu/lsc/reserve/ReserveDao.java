package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve;

import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain.ReserveDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReserveDao {
    void insertReserve(ReserveDomain reserveDomain);

    public ReserveDomain selectMaxSqnc();

    List<ReserveDomain> selectMyReserveList(ReserveDomain reserveDomain);

    List<ReserveDomain> selectMyReserveListDetail(ReserveDomain reserveDomain);
}
