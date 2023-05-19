package com.jirepo.hrm.org.sql;

import org.apache.ibatis.jdbc.SQL;


/**
 * 조직도 쿼리를 정의한다.
 */
public class OrgEmployeeSqlBuilder {


  /**
   * 부서의 직원들을 조회하는 쿼리를 빌드한다. 
   * @return  쿼리 
   */
  public static String getEmployeesOfDept() {
    return new SQL() {{
      SELECT("A.*, C.PROFILE_IMG");
      FROM("ORG_EMPLOYEE A ");
      INNER_JOIN("ORG_MY_JOB B  ON A.EMP_ID = B.EMP_ID");
      INNER_JOIN("ORG_PERSON C  ON A.PSN_ID = C.PSN_ID");
      WHERE("B.DEPT_ID = #{deptId}");
    }}.toString();
  }//:


  /**
   * 하위 부서 목록을 반환하는 쿼리를 빌드한다. 
   * @param upDeptId
   * @return 쿼리 
   */
  public static String getSubDepartments(String upDeptId) {
    return new SQL() { 
      {
        SELECT("DEPT_ID, DEPT_LOC, DEPT_NAME, dept_sort_ordr  ");
        FROM("org_department");
        WHERE("UP_DEPT_ID = #{upDeptId}");
        WHERE("dept_status = 'U'");
        ORDER_BY("dept_sort_ordr");
      }

    }.toString();
    
  }//:


  /**
   * 직원정보를 조회하는 쿼리를 빌드한다.
   * @return 쿼리 
   */
  public static String getEmployeeInfo() {
    return new SQL() {
      {
        SELECT("A.EMP_ID, A.OFC_ID, A.DEPT_ID, A.CMP_ID");
        SELECT("B.USER_NAME, B.CMP_EMAIL, B.RETIRE_DATE, B.ECNY_DATE");
        SELECT("C.PSN_ID, C.CELL_PHONE , C.PROFILE_IMG,  C.BIRTHDATE");
        SELECT("D.POS_ID, D.POS_NAME");
        SELECT("E.OFC_NAME");
        SELECT("F.DEPT_NAME ");
        FROM("ORG_MY_JOB A");
        INNER_JOIN("ORG_EMPLOYEE   B ON A.EMP_ID = B.EMP_ID");
        INNER_JOIN("ORG_PERSON     C ON B.PSN_ID = C.PSN_ID");
        INNER_JOIN("ORG_POSITION   D ON B.POS_ID = D.POS_ID");
        INNER_JOIN("ORG_OFFICE     E ON E.OFC_ID = A.OFC_ID");
        INNER_JOIN("ORG_DEPARTMENT F ON F.DEPT_ID = A.DEPT_ID");
        WHERE("A.EMP_ID = #{empId}");
        WHERE("A.BASS_DEPT_YN = 'Y'");
      }
    }.toString();

  }//:

}///~
