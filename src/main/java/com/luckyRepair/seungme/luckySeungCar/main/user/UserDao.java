package com.luckyRepair.seungme.luckySeungCar.main.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Mapper
@Repository
public interface UserDao {
    void insertUserBase(UserDomain userDomain);

    void insertUserInfo(UserDomain userDomain);

    public UserDomain findUserByUserInfo(UserDomain domain);
    public UserDomain findUserByUserInfo2(UserDomain domain);
    UserDomain findUserById(String userIdnt);
}
