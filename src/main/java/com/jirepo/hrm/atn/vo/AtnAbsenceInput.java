package com.jirepo.hrm.atn.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
public class AtnAbsenceInput {
    private String empId;
    private String absnYy;
    private String absnMm;
}
