package com.jirepo.hrm.email.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/** 메일목록 VO이다. */
@Getter
@Setter
public class EmailVo {
    /** 메일 아이디 */
    private String mailId; 
    /** 메일 메시지 아이디  */
    private String msgId; 
    /** 메일 제목  */
    private String subject;
    /** 메일 수신 날짜시간 */ 
    private LocalDateTime mailRecvDt; 
    /** 메일 발송 날짜시간 */
    private LocalDateTime mailSendDt; 
    
}///~
