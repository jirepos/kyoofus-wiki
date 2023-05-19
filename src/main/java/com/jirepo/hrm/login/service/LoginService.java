package com.jirepo.hrm.login.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.jirepo.hrm.login.mapper.LoginMapper;
import com.jirepo.hrm.login.vo.LoginUserInput;
import com.jirepo.hrm.login.vo.LoginUserVo;


/** 로그인 처리 서비스 클래스이다.  */
public class LoginService {
    @Autowired
    private LoginMapper loginMapper; 
    public LoginUserVo login(LoginUserInput input) {
        LoginUserVo vo =  loginMapper.selectUserInfo(input);
        if(vo == null) {
            throw new RuntimeException("사용자 정보가 없습니다.");
        }
        // 비빌번호 체크
        // 로그인 실패 횟수 체크
        // 로그인 실패 횟수가 5회 이상이면 로그인 차단
        // 로그인 차단이면 로그인 차단 메시지를 보여준다.
        // 로그인 차단이 아니면 로그인 성공 메시지를 보여준다.
        // JWT 생성 
        return vo; 
    }//:
    
}///~
