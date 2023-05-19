package com.jirepo.hrm.root.resolver.mutation;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

/**
 * Root Schema를 정의하기 위한 더미 리졸버이다. 
 */
@Component 
public class RootMutationResolver  implements GraphQLMutationResolver {
    /**
     * 더미 뮤테이션이다. 
     * @return 더미 뮤테이션 결과 값을 반환한다. 
     */
    public String dummyMutation(String id) {
        return "dummy";
    }
}///~
