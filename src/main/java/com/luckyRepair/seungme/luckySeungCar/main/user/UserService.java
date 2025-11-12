package com.luckyRepair.seungme.luckySeungCar.main.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserDao userDao;

    public void insertUser(UserDomain userDomain){
        userDao.insertUser(userDomain);
    }
}
