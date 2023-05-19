package com.jirepo.hrm.org.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.jirepo.core.config.jdbc.SecondaryMapper;
import com.jirepo.hrm.org.sql.OrgEmployeeSqlBuilder;
import com.jirepo.hrm.org.vo.OrgDepartment;
import com.jirepo.hrm.org.vo.OrgEmployee;
import com.jirepo.hrm.org.vo.OrgEmployeeInfo;

/**
 * 직원정보 Mapper 이다. 
 */
@SecondaryMapper
public interface OrgEmployeeMapper {


  /**
   * 부서의 직원을 조회한다. 
   * @param deptId 부서아이디 
   * @return 직원정보 
   */
  @SelectProvider(type = OrgEmployeeSqlBuilder.class, method = "getEmployeesOfDept")
  List<OrgEmployee> getEmployeesOfDept(String deptId);

  /**
   * 부서의 하위 부서를 조회한다. 
   * @param deptId 상위부서 아이디 
   * @return 부서정보 
   */
  //@SelectProvider(type = OrgEmployeeSqlBuilder.class, method = "getSubDepartments")
  List<OrgDepartment> getSubDepartments(String deptId);

  /**
   * 직원정보를 조회한다. 
   * @param empId  사원아이디 
   * @return  사원정보 
   */
  @SelectProvider(type = OrgEmployeeSqlBuilder.class, method = "getEmployeeInfo")
  OrgEmployeeInfo getEmployeeInfo(String empId);
  
  
}///~
