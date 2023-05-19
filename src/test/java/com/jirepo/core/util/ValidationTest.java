package com.jirepo.core.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import com.jirepo.hrm.org.vo.OrgEmpMemo;

public class ValidationTest {

    
    @Test 
    public void testValidation(){ 

        // https://kapentaz.github.io/java/Java-Bean-Validation-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90/#
        // https://velog.io/@_koiil/SpringBoot-Spring-Validation%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%A6%9D
        //  https://www.baeldung.com/javax-validation
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        OrgEmpMemo memo = new OrgEmpMemo(); 
        //memo.setEmpId("aaa");
        Set<ConstraintViolation<OrgEmpMemo>> violations = validator.validate(memo);
        // 오륙 건수 
        System.out.println( violations.size()); 

        for (ConstraintViolation<OrgEmpMemo> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }


    @Test 
    public void testValidationWithException() throws Exception { 

        // https://kapentaz.github.io/java/Java-Bean-Validation-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90/#
        // https://velog.io/@_koiil/SpringBoot-Spring-Validation%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%9C%A0%ED%9A%A8%EC%84%B1-%EA%B2%80%EC%A6%9D
        //  https://www.baeldung.com/javax-validation
        // https://reflectoring.io/bean-validation-with-spring-boot/
        // https://www.baeldung.com/spring-service-layer-validation   // 서비스 클래스에 validator 적용 
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        OrgEmpMemo memo = new OrgEmpMemo(); 
        //memo.setEmpId("aaa");
        Set<ConstraintViolation<OrgEmpMemo>> violations = validator.validate(memo);
        // 오륙 건수 
        System.out.println( violations.size()); 
        // 예외 처리 
        if(!violations.isEmpty()) {
            // 예외 던진다. 
            throw new ConstraintViolationException(violations);
        }

    }//: 
    
}///~
