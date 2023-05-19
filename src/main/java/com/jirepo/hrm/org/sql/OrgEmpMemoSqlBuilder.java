package com.jirepo.hrm.org.sql;

import org.apache.ibatis.jdbc.SQL;

import com.jirepo.hrm.org.vo.OrgEmpMemo;

/**
 * 직원 메모 쿼리 빌더이다. 
 */
public class OrgEmpMemoSqlBuilder {


    /**
     * 직원 메모 정보를 조회한다.
     * @param empId 직원 아이디
     * @return 직원 메모 정보
     */
    public static String selectOfEmp(String empId){ 
        return new SQL() {
            {
                SELECT("*");
                FROM("ORG_EMP_MEMO");
                WHERE("EMP_ID = #{empId}");
                ORDER_BY("UPD_DT DESC");
            }
        }.toString();
    }//:


    /**
     * 메모 입력 쿼리를 빌드한다.
     * @param momo  메모 정보
     * @return  쿼리 
     */
    public static String insert(OrgEmpMemo momo) {
        return new SQL() {
            {
                INSERT_INTO("org_emp_memo");
                VALUES("memo_id", "#{memoId}");
                VALUES("emp_id", "#{empId}");
                VALUES("memo", "#{memo}");
                VALUES("reg_dt", "#{regDt}");
                VALUES("upd_dt", "#{updDt}");
            }
        }.toString(); 
    }//:

    /**
     * 메모 수정 쿼리를 빌드한다.
     * @param momo  
     * @return 쿼리 
     */
    public static String update(OrgEmpMemo momo){ 
        return new SQL() {
            {
                UPDATE("org_emp_memo");
                SET("memo = #{memo}");
                SET("upd_dt = #{updDt}");
                WHERE("memo_id = #{memoId}");
            }
        }.toString(); 
    }//:

    /**
     * 메모 삭제 쿼리를 빌드한다.
     * @param memo 메모 정보 
     * @return  쿼리 
     */
    public static String delete() {
        return new SQL() {
            {
                DELETE_FROM("org_emp_memo");
                WHERE("memo_id = #{memoId}");
            }
        }.toString();
    }//:
    
}///~
