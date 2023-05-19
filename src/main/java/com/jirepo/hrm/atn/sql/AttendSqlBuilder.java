package com.jirepo.hrm.atn.sql;

import org.apache.ibatis.jdbc.SQL;

import com.jirepo.hrm.atn.vo.AtnAbsenceInput;

public class AttendSqlBuilder {

  public static String getAttendCards() {
    return new SQL() {
      {
        SELECT("*");
        FROM("ATN_CARD");
        WHERE("EMP_ID = #{empId}");
        ORDER_BY("ATTN_DATE DESC");
      }
    }.toString();

  }// :


  public static String getAttendCardsBetween() {
    return new SQL() {
      {
        SELECT("*");
        FROM("ATN_CARD");
        WHERE("EMP_ID = #{empId}");
        WHERE("ATTN_DATE BETWEEN #{startDate} AND #{endDate}");
        //ORDER_BY("ATTN_DATE DESC");
      }
    }.toString();

  }// :


  /**
   * 부재정보 SQL 
   * @return
   */
  public static String getAbsences(AtnAbsenceInput input) {
    return new SQL() {
      {
        SELECT("EMP_ID, ABSN_BGN_DE, ABSN_YY, ABSN_MM, ABSN_END_DE, ABSN_WHY, ALL_DAY_YN ");
        FROM("CMM_ABSENCE ");
        WHERE("EMP_ID = #{empId}");
        WHERE("ABSN_YY = #{absnYy} ");
        if(input.getAbsnMm() != null) {
          WHERE("ABSN_MM = #{absnMm} ");
        }
        ORDER_BY("ABSN_BGN_DE DESC ");
      }
    }.toString();
  }

  public static String getAnnLeaveStat() {
    return new SQL() {
      {
        SELECT("EMP_ID, ACCT_BGN_DATE, TOT_YHLD_CNT, USE_YHLD_CNT, REMD_YHLD_CNT ");
        FROM("ATN_YEAR_HOLIDAY");
        WHERE("EMP_ID = #{empId}");
        WHERE("ACCT_BGN_DATE LIKE  CONCAT(#{year}, '%')");
      }
    }.toString();
  }//:

}/// ~
