package com.luckyRepair.seungme.luckySeungCar.main.user;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDomain {
    private String userIdnt;
    private String userPswd;
    private String userName;
    private String elecMail;
    private String teleHand;
    private Date workDate;
    private Date updtDate;
    private String workIdnt;
    private String workIpas;
}
