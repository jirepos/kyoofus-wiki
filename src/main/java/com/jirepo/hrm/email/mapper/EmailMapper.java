package com.jirepo.hrm.email.mapper;

import java.util.List;

import com.jirepo.core.config.jdbc.SecondaryMapper;
import com.jirepo.hrm.email.vo.EmailSearchInput;
import com.jirepo.hrm.email.vo.EmailVo;

@SecondaryMapper
public interface EmailMapper {

    /** 
     * 나에게 보낸 메일 조회하기 
     * @param vo  조회 조건 
     * @return  메일 목록 
     */
    List<EmailVo> selectMyEmailsByFrom(EmailSearchInput input);
    
}///~
