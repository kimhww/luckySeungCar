package com.luckyRepair.seungme.luckySeungCar.main.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
/*@RequiredArgsConstructor*/
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDomain findUserByUserinfo(UserDomain domain) {
        UserDomain findDomain = userDao.findUserByUserInfo(domain);
        return findDomain;
    }

    public UserDomain findUserByUserIdnt(String userIdnt) {
        UserDomain findDomain = userDao.findUserById(userIdnt);
        return findDomain;
    }

    public boolean isExistsUser(UserDomain domain) {
        return (findUserByUserinfo(domain) != null);
    }

    public boolean isDuplicateUser(String userIdnt) {
        return (findUserByUserIdnt(userIdnt) != null);
    }

    @Transactional
    public void insert(UserDomain domain) {
        String encPassword = passwordEncoder.encode(domain.getUserPswd());
        log.debug("encoded Password : {}", encPassword);
        domain.setUserPswd(encPassword);

        userDao.insertUserBase(domain);
        userDao.insertUserInfo(domain);
    }
}
