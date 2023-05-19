package com.jirepo.hrm.org.resolver.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jirepo.hrm.org.mapper.OrgEmpMemoMapper;
import com.jirepo.hrm.org.vo.OrgEmpMemo;

import lombok.extern.slf4j.Slf4j;


/**
 * 직원 메모 Query Resolver이다. 
 */
@Slf4j
@Component
public class OrgEmpMemoQueryResolver implements GraphQLQueryResolver {
    /** 직원 메모 매퍼 */
    @Autowired
    private OrgEmpMemoMapper orgEmpMemoMapper;

    /**
     * 직원 메모들을 조회한다. 
     * @param empId 직원 ID
     * @return  직원 메모 목록
     */
    public List<OrgEmpMemo> getMemosOfEmp(String empId) {
        log.info("selectOfEmp: {}", empId);
        return orgEmpMemoMapper.selectOfEmp(empId);
    }//:

}///~