package com.jirepo.hrm.org.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgEmployeeInfo {

    protected String psnId; 
    protected String cellPhone; 
    protected String profileImg; 
    protected String birthDate; 


    protected String cmpId;
    protected String empId; 
    protected String userName; 
    protected String psnEmail;
    protected String cmpEmail;
    protected String userId; 
    protected int secLvl; 
    protected LocalDateTime updDt;
    protected String retireDate; 
    protected String ecnyDate; 


    protected String deptId; 
    protected String deptName; 
    protected String posId; 
    protected String posName; 
    protected String ofcId; 
    protected String ofcName;
}
