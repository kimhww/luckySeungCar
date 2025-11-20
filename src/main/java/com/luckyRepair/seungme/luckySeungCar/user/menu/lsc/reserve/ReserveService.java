package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve;

import com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain.ReserveDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ReserveService {
    @Autowired
    ReserveDao reserveDao;

    @Transactional
    public void insertReserve(ReserveDomain domain) {
        reserveDao.insertReserve(domain);
    }

    public ReserveDomain selectMaxSqnc() {
        return reserveDao.selectMaxSqnc();
    }

    public List<ReserveDomain> selectMyReserveList(ReserveDomain reserveDomain) {
        return reserveDao.selectMyReserveList(reserveDomain);
    }
}
