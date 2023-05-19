package com.jirepo.hrm.login.vo;

import lombok.Getter;
import lombok.Setter;


/**
 * 로그인 사용자 정보 
 */
@Getter
@Setter
public class LoginUserVo {
    /** 사원 아이디 */
    private String empId;
    /** 사용자 아이디 */
    private String userId;
    /** 비밀번호  */
    private String loginPwd;
    /** 로그인 허용 여부 */
    private String loginDenyYn;
    /** 사용자 상태 */
    private String empStatus; 
    
}
