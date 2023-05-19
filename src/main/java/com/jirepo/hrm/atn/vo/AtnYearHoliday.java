package com.jirepo.hrm.atn.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
public class AtnYearHoliday {
    private String empId;
    private String acctBgnDate;
    private int totYhldCnt;
    private int useYhldCnt;
    private int remdYhldCnt;
}///~