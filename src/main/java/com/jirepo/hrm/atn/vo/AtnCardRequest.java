package com.jirepo.hrm.atn.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AtnCardRequest {
    private String empId; 
    private String startDate;
    private String endDate; 
}
