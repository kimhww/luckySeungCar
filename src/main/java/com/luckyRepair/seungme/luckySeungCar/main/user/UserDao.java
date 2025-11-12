package com.luckyRepair.seungme.luckySeungCar.main.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Mapper
@Repository
public interface UserDao {
    void insertUser(UserDomain userDomain);
}
