package com.jirepo.hrm.org.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter 
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class OrgDepartment {
    private String deptId; 
    private String deptName; 
    private String deptLoc;
    private int deptSortOrdr;
}
