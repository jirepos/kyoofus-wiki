
/* 직원 메모 ddl */ 
create table org_emp_memo (
  memo_id varchar(32) not null, 
  emp_id varchar(25) not null,
  memo varchar(1000) not null,
  reg_dt char(8) not null,
  upd_dt timestamp,
  primary key (memo_id)
);


