package com.jirepo.hrm.org;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jirepo.HrmApplication;
import com.jirepo.hrm.org.mapper.OrgEmployeeMapper;
import com.jirepo.hrm.org.vo.OrgEmployee;


/**
 * GraphQL 때문인지는 모르지만 JunitTest가 실행이 안된다. 
 */
@SpringBootTest(classes = HrmApplication.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = HrmApplication.class)
public class OrgMapperTest {
  @Autowired
  private OrgEmployeeMapper employeeMapper;

  @Test
  public void testGetAllemployees() {
    try {
      String deptId = "1111";
      List<OrgEmployee> orgEmployees = employeeMapper.getEmployeesOfDept(deptId);
      orgEmployees.forEach(employee -> System.out.println(employee.getUserName()));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }// :
}/// ~
