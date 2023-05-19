package com.jirepo.hrm.email.vo;

import lombok.Getter;
import lombok.Setter;


/**
 * 메일 조회조건 VO이다. 
 */
@Getter
@Setter
public class EmailSearchInput {

    /** Database */
    private String dbType; 
    /** 메일 종류(in:받은, out:보낸) */
    private String inOutType; // 
    /** 메일 수신/발송 날짜 시간 */
    private String recvSendDt; 
    /** 보낸 사람/받는 사람 */
    private String fromOrTo; 
    /** 나의 이메일 */
    private String loginUserEmail; 
    /** 날짜 이전 메일 false, 날짜 이후 메일 true */
    private boolean beforeDt; 
}///~
