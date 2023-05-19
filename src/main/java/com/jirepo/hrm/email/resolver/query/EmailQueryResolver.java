package com.jirepo.hrm.email.resolver.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jirepo.core.util.DateTimeUtils;
import com.jirepo.hrm.email.mapper.EmailMapper;
import com.jirepo.hrm.email.vo.EmailSearchInput;
import com.jirepo.hrm.email.vo.EmailVo;

/**
 * Email Query Resolver이다. 
 */
@Component
public class EmailQueryResolver implements GraphQLQueryResolver {

    /** 이메일 매퍼이다.  */
    @Autowired 
    private EmailMapper emailMapper; 

    
    /**
     * 보낸 사람이 나에게 보낸 메일을 조회한다. 
     * @param input 검색조건 
     * @return  메일 목록 
     */
    public List<EmailVo> getMyEmailsByFrom(EmailSearchInput input) {
        if(input.getRecvSendDt() == null || input.getRecvSendDt().isBlank()) {
            if(input.getInOutType() == null || input.getInOutType().isBlank()) {
                input.setInOutType("in");
            }
            input.setRecvSendDt(DateTimeUtils.format(DateTimeUtils.systemTime()));
            input.setBeforeDt(true);
        }
        input.setLoginUserEmail("sanghyun@naonsoft.com");
        return this.emailMapper.selectMyEmailsByFrom(input);
    }//:

}///~