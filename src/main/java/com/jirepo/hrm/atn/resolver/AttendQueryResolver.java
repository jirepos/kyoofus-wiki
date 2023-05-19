package com.jirepo.hrm.atn.resolver;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jirepo.core.util.DateTimeUtils;
import com.jirepo.hrm.atn.mapper.AttendMapper;
import com.jirepo.hrm.atn.vo.AtnAbsence;
import com.jirepo.hrm.atn.vo.AtnAbsenceInput;
import com.jirepo.hrm.atn.vo.AtnCardRequest;
import com.jirepo.hrm.atn.vo.AtnYearHoliday;
import com.jirepo.hrm.atn.vo.AttendCard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AttendQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private AttendMapper attendMapper;
    

  public List<AttendCard> getAttendCards(String empId) {
    return this.attendMapper.getAttendCards(empId);
  }



  public List<AttendCard> getAttendCardsOfThisWeek(String empId) {
    LocalDate[] dates = DateTimeUtils.getStartAndEndDate(DateTimeUtils.systemTime().toLocalDate());
    AtnCardRequest request = AtnCardRequest.builder()
        .empId(empId)
        .startDate(  DateTimeUtils.format(dates[0], "yyyyMMdd") )
        .endDate(DateTimeUtils.format(dates[1], "yyyyMMdd"))
        .build();
    return attendMapper.getAttendCardsBetween(request);
  }// :

    /** 부재정보 조회 */
  // query GET_ABSENCE($input: AtnAbsenceInput) {
  //   absences(input: $input) {
  //     empId,
  //     absnYy,
  //     absnMm,
  //     absnBgnDe,
  //     absnEndDe,
  //     absnWhy, 
  //     allDayYn
  //   }
    
  //   {
  //     "input":  { 
  //         "empId": "M651491304", 
  //         "absnYy": "2022", 
  //         "absnMm": "06" 
  //     }
  //   }
  public List<AtnAbsence> getAbsences(AtnAbsenceInput input) {
    log.debug("부재정보 조회 : {}", input.getEmpId());
    return this.attendMapper.getAbsences(input);
  }//:


  /**
   * 연차 상태 조회 
   * @param empId 사원아이디 
   * @param year 연도 예) 2022 
   * @return
   *  연차상태 
   */
  public List<AtnYearHoliday> getAnnLeaveStat(String empId, String year) {
    log.debug("empId {}", empId); 
    log.debug("year {}", year); 
    List<AtnYearHoliday> list = this.attendMapper.getAnnLeaveStat(empId, year );  
    log.debug("AtnYearHoliday {}", list.get(0));
    return list; 
  }//:

  public List<AttendCard> getAttendCardsOfWeek(String empId, String dateStr) {
    LocalDate baseDate = null;
    if (dateStr == null || "".equals(dateStr)) {
      baseDate = DateTimeUtils.systemTime().toLocalDate();
    } else {
      dateStr = dateStr.replaceAll("-", "");
      baseDate = DateTimeUtils.parseToLocalDate(dateStr, "yyyyMMdd");
    }

    LocalDate[] dates = DateTimeUtils.getStartAndEndDate(baseDate);
    AtnCardRequest request = AtnCardRequest.builder()
        .empId(empId)
        .startDate(DateTimeUtils.format(dates[0], "yyyyMMdd"))
        .endDate(DateTimeUtils.format(dates[1], "yyyyMMdd"))
        .build();
    return attendMapper.getAttendCardsBetween(request);
  }// :
}
