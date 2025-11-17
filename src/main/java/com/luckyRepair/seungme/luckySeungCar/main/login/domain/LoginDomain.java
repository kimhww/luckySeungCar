package com.luckyRepair.seungme.luckySeungCar.main.login.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginDomain {
    private String userIdnt;
    private String userPswd;
    private String grupCode;
    private Date lognDate;
    private String lognIpas;
}
