package com.jirepo.hrm.org.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * 사원 메모 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgEmpMemo {
    private String memoId; 
    @NotBlank(message="사원ID는 필수입력입니다.") 
    private String empId; 
    @NotBlank(message = "메모는 필수입력입니다.")
    private String memo;
    private String regDt; 
    private LocalDateTime updDt; 
}
