package com.jirepo.hrm.org.vo;

import java.time.LocalDateTime;

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
public class OrgEmployee extends OrgPerson {
  protected String cmpId;
  protected String empId; 
  protected String userName; 
  protected String psnEmail;
  protected String cmpEmail;
  protected String userId; 
  protected int secLvl; 
  protected LocalDateTime updDt;
  protected String retireDate; 
  protected String encyDate; 
}///~
