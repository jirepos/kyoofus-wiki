package com.jirepo.hrm.org.resolver.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jirepo.hrm.org.mapper.OrgEmployeeMapper;
import com.jirepo.hrm.org.vo.OrgDepartment;
import com.jirepo.hrm.org.vo.OrgEmployee;
import com.jirepo.hrm.org.vo.OrgEmployeeInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 조직도 Query Resolver 
 */
@Slf4j
@Component
public class OrgEmployeeQueryResolver implements GraphQLQueryResolver {

  /** 직원 Mapper */
  @Autowired
  private OrgEmployeeMapper employeeMapper;
  

  /**
   * 부서 직원 목록 조회 
   * @param deptId 부서아이디 
   * @return
   *  직원목록 
   */
  public List<OrgEmployee> getEmployeesOfDept(String deptId) {
    return employeeMapper.getEmployeesOfDept(deptId);
  }


  /**
   * 하위부서 목록 조회 
   * @param upDeptId 상위부서아이디
   * @return
   *  부서 목록 
   */
  public List<OrgDepartment> getSubDepartments(String upDeptId) {
    return employeeMapper.getSubDepartments(upDeptId);
  }




//   query GET_ANN_LEAVE_STAT($empId: String, $year: String ) {
//     annLeaveStat(empId: $empId, year: $year) {
//       empId,
//       acctBgnDate,
//       totYhldCnt,
//       useYhldCnt,
//       remdYhldCnt
//     }
// }
// {
//   "empId": "M651491304",
//   "year": "2022"
// }



  /**
   * 직원정보 조회 
   * @param empId 사원아이디 
   * @return
   *   직원정보
   */
  public OrgEmployeeInfo getEmployeeInfo(String empId) {
    return this.employeeMapper.getEmployeeInfo(empId);
  }//:


}////~
