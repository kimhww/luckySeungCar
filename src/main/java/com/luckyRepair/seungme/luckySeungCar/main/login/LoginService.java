package com.luckyRepair.seungme.luckySeungCar.main.login;

import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginDomain;
import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginUserInfoDomain;
import com.luckyRepair.seungme.luckySeungCar.main.user.UserDao;
import com.luckyRepair.seungme.luckySeungCar.main.user.UserDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LoginService {

    @Autowired
    LoginDao loginDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public LoginUserInfoDomain loginProcess(LoginDomain domain) throws LoginFailException{
        LoginDomain dbData =  loginDao.findUserById(domain.getUserIdnt());
        log.debug("db User : {} " , dbData);

        if(dbData == null) throw new LoginFailException("no exists User");
        if(!passwordEncoder.matches(domain.getUserPswd(), dbData.getUserPswd()))
            throw new LoginFailException("not match password.");

        UserDomain user = userDao.findUserById(domain.getUserIdnt());
        loginDao.updateLoginInfo(domain);

        LoginUserInfoDomain loginUserInfo = LoginUserInfoDomain.builder()
            .userIdnt(domain.getUserIdnt())
            .userName(user.getUserName())
            .groupCode(dbData.getGrupCode())
            .loginDate(domain.getLognDate())
            .loginIp(domain.getLognIpas())
            .prevLoginDate(dbData.getLognDate())
            .prevLoginIp(dbData.getLognIpas())
            .build();

        log.info("login success : {}", loginUserInfo);
        return loginUserInfo;
    }

    public UserDomain findUser(UserDomain domain) {
        UserDomain findDomain = userDao.findUserByUserInfo2(domain);
        return findDomain;
    }
}
