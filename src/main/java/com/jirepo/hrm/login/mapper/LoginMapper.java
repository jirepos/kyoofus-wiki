package com.jirepo.hrm.login.mapper;

import org.apache.ibatis.annotations.SelectProvider;

import com.jirepo.core.config.jdbc.SecondaryMapper;
import com.jirepo.hrm.login.sql.LoginSqlBuilder;
import com.jirepo.hrm.login.vo.LoginUserInput;
import com.jirepo.hrm.login.vo.LoginUserVo;

/** 로그인 처리와 관련된 Mapper이다. */
@SecondaryMapper
public interface LoginMapper {

    /**
     * 사용자 정보 조회
     * @param input 조회 조건
     * @return  사용자 정보 
     */
    @SelectProvider(type = LoginSqlBuilder.class, method = "selectUserInfo")
    LoginUserVo selectUserInfo(LoginUserInput input);

}/// ~
