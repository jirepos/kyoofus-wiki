package com.jirepo.hrm.login.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * 로그인에서 사용하는 SQL을 생성하는 클래스
 */
public class LoginSqlBuilder {

    /**
     * 사용자 정보 조회 
     * @return 사용자 정보 조회 SQL
     */
    public static String selectUserInfo() {
        return new SQL() {
            {
                SELECT("EMP_ID, USER_ID, LOGIN_PWD, LOGIN_DENY_YN, EMP_STATUS ");
                FROM("ORG_EMPLOYEE");
                WHERE("USER_ID = #{userId}");
            }
        }.toString();
    }//:
    
}///~
