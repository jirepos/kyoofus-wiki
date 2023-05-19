package com.jirepo.hrm.org.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.jirepo.core.config.jdbc.PrimaryMapper;
import com.jirepo.hrm.org.sql.OrgEmpMemoSqlBuilder;
import com.jirepo.hrm.org.vo.OrgEmpMemo;

/** 
 * 직원 메모 Mapper이다. 
 */
@PrimaryMapper
public interface OrgEmpMemoMapper {

    /**
     * 직원 메모 정보를 조회한다.
     * @param empId 직원 아이디
     * @return 직원 메모 정보
     */
    @SelectProvider(type = OrgEmpMemoSqlBuilder.class, method = "selectOfEmp")
    List<OrgEmpMemo> selectOfEmp(String empId);
    
    /**
     * 직원 메모를 추가한다.
     * @param orgEmpMemo 직원 메모 
     * @return 건수 
     */
    @InsertProvider(type = OrgEmpMemoSqlBuilder.class, method = "insert")
    int insert(OrgEmpMemo orgEmpMemo);
    /**
     * 직원 메모를 수정한다.
     * @param orgEmpMemo    직원 메모
     * @return 건수 
     */
    @UpdateProvider(type = OrgEmpMemoSqlBuilder.class, method = "update")
    int update(OrgEmpMemo orgEmpMemo);
    /**
     * 직원 메모를 삭제한다.
     * @param orgEmpMemo   직원 메모
     * @return  건수 
     */
    @DeleteProvider(type = OrgEmpMemoSqlBuilder.class, method = "delete")
    int delete(String memoId);
    
}///~
