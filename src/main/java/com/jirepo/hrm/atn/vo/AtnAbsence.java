package com.jirepo.hrm.atn.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtnAbsence {
    private String empId;
    private String absnYy;
    private String absnMm;
    private String absnWhy; 
    private String absnBgnDe;
    private String absnEndDe;
    private String allDayYn;
}
