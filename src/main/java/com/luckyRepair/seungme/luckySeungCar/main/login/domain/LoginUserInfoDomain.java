package com.luckyRepair.seungme.luckySeungCar.main.login.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginUserInfoDomain {
    private String userIdnt;
    private String userName;
    private Date loginDate;
    private String loginIp;
    private String groupCode;
    private Date prevLoginDate;
    private String prevLoginIp;
}
