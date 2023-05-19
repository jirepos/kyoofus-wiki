package com.jirepo.hrm.atn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.jirepo.core.config.jdbc.SecondaryMapper;
import com.jirepo.hrm.atn.sql.AttendSqlBuilder;
import com.jirepo.hrm.atn.vo.AtnAbsence;
import com.jirepo.hrm.atn.vo.AtnAbsenceInput;
import com.jirepo.hrm.atn.vo.AtnYearHoliday;
import com.jirepo.hrm.atn.vo.AttendCard;
import com.jirepo.hrm.atn.vo.AtnCardRequest;

@SecondaryMapper
public interface AttendMapper {
  @SelectProvider(type = AttendSqlBuilder.class, method = "getAttendCards")
  List<AttendCard> getAttendCards(String empId);
  @SelectProvider(type = AttendSqlBuilder.class, method = "getAttendCardsBetween")
  List<AttendCard> getAttendCardsBetween(AtnCardRequest request);
  /**
   * 부재정보 조회 
   * @param atnAbsence  부재정보 조회 파라미터 
   * @return
   *  부재정보 목록 
   */
  @SelectProvider(type = AttendSqlBuilder.class, method = "getAbsences")
  List<AtnAbsence> getAbsences(AtnAbsenceInput atnAbsence);

  /**
   * 연차 상태 
   * @param empId 사원아이디 
   * @param year  년도 예) 2022 
   * @return
   *  연차 상태 
   */
  @SelectProvider(type = AttendSqlBuilder.class, method = "getAnnLeaveStat")
  List<AtnYearHoliday>  getAnnLeaveStat(String empId, String year);
}///~
