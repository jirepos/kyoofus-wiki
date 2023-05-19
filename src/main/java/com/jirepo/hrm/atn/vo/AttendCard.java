package com.jirepo.hrm.atn.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class AttendCard {
  private String cmpId; 
  private String empId; 
  private String attnCardDate;
  private String attnDate; 
  private String attnYy; 
  private String attnTime; 
  private String lvofTime; 
  private Date updDt; 

}///~
