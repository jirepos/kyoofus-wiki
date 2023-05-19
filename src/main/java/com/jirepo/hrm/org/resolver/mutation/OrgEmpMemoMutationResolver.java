package com.jirepo.hrm.org.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jirepo.core.util.DateTimeUtils;
import com.jirepo.core.util.IdGenerator;
import com.jirepo.hrm.org.mapper.OrgEmpMemoMapper;
import com.jirepo.hrm.org.vo.OrgEmpMemo;


/**
 * 직원 메모 뮤테이션 리졸버이다. 
 */
@Component
public class OrgEmpMemoMutationResolver implements GraphQLMutationResolver {
    /** 직원 Mapper */
    @Autowired
    private OrgEmpMemoMapper orgEmpMemoMapper;

    /**
     * 메모를 추가한다. 
     * @param empId 직원 ID
     * @param memo  메모 내용
     * @return  추가된 메모 객체
     */
    public OrgEmpMemo insertMemo(String empId, String memo) {
        OrgEmpMemo empMemo = new OrgEmpMemo();
        empMemo.setMemoId(IdGenerator.randomUUID2());
        System.out.println(empMemo.getMemoId());
        empMemo.setEmpId(empId);
        empMemo.setMemo(memo);
        empMemo.setUpdDt(DateTimeUtils.systemTime());
        empMemo.setRegDt(DateTimeUtils.format(empMemo.getUpdDt()));
        this.orgEmpMemoMapper.insert(empMemo);
        return empMemo;
    }// :

    /**
     * 직원 메모를 삭제한다. 
     * @param memoId
     * @return  삭제된 메모 객체
     */
    public String deleteMemo(String memoId) {
        this.orgEmpMemoMapper.delete(memoId);
        return memoId;
    }// :
    

}/// ~
