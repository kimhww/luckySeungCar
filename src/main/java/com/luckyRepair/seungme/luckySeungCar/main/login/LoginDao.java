package com.luckyRepair.seungme.luckySeungCar.main.login;

import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginDao {
    public LoginDomain findUserById(String userIdnt);
    public void updateLoginInfo(LoginDomain domain);
}
