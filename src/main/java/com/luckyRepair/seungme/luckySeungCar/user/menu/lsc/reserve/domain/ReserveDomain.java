package com.luckyRepair.seungme.luckySeungCar.user.menu.lsc.reserve.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveDomain {
    private Integer resvSqnc;
    private String userIdnt;
    private String cateGub1;
    private String cgb1Knam;
    private String cateGub2;
    private String cgb2Knam;
    private String resvDate;
    private String resvTime;
    private String carrBrnd;
    private String carrModl;
    private String resvDesc;
    private String rqstOpt1;
    private String rqstOpt2;
    private String rqstOpt3;
    private String rqstOpt4;
    private String delcYorn;
    private String resvStat;
    private String resvKnam;
    private Date workDate;
    private String workIdnt;
    private String workIpas;

    private Integer maxResvSqnc;
}
