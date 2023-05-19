package com.jirepo.hrm.login.vo;

import lombok.Getter;
import lombok.Setter;


/**
 * 로그인 시 사용자 정보 조회 파라미터 클래스. 
 */
@Getter
@Setter
public class LoginUserInput {
    /** 사용자 아이디 */
    private String userId; 
    /** 비밀번호 */
    private String loginPwd;
}///~
