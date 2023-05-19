package com.jirepo.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * IdGenerator 클래스 테스트 이다.
 */
public class IdGeneratorTest {


    /**
     * UUID 생성 테스트. 
     */
    @Test
    public void testRandomUUID() {
        String uuid = IdGenerator.randomUUID();
        assertNotNull(uuid);
        assertEquals(36, uuid.length());
        System.out.println(uuid);
    }//:


    /**
     * UUID 생성 후 대쉬를 제거한 테스트. 
     */
    @Test
    public void testRandomUUID2() {
        String uuid = IdGenerator.randomUUID2();
        assertNotNull(uuid);
        assertEquals(32, uuid.length());
        System.out.println(uuid);
    }//:
    
}///~
