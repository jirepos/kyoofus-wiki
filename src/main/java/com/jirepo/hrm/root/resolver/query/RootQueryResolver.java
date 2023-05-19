package com.jirepo.hrm.root.resolver.query;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

/**
 * 루트 쿼리를 생성하기 위한 더미 쿼리 리졸버이다. 
 */
@Component 
public class RootQueryResolver implements GraphQLQueryResolver {
    /**
     * 더미 쿼리이다. 
     * @return 더미 쿼리 결과 값을 반환한다. 
     */
    public String dummyQuery() {
        return "dummy";
    }
}///~
