


-- 특정 사용자가 나에게 보낸 메일 
-- 메일을 보낸 사람을 찾기가 어렵네  
SELECT X.*, B.MAIL_ID , B.MSG_ID, C.SUBJECT, B.MAIL_RECV_DT  
FROM (
      SELECT A.MAILBOX_ID, A.PHYSICAL_NAME  
      FROM EML_MAILBOX A
      WHERE EMAIL  = 'sanghyun@naonsoft.com'
     ) X
INNER JOIN EML_MAIL B ON B.MAILBOX_ID = X.MAILBOX_ID      
INNER JOIN EML_MAIL_MESSAGE C  ON C.MSG_ID = B.MSG_ID 
WHERE X.PHYSICAL_NAME NOT IN ('Sent', 'Spam', 'Drafts', '보낸 편지함', '지운 편지함' )     
AND B.MAIL_DEL_YN = 'N' 
AND B.MAIL_RECV_DT BETWEEN TO_DATE('20220101000000', 'YYYYMMDDHH24MISS') AND TO_DATE('20220701235959', 'YYYYMMDDHH24MISS')
AND C.MAIL_FROM LIKE '%barnlife@naonsoft.com%'
ORDER BY B.MAIL_RECV_DT  DESC 
-- 


-- 받은 시간  이후에 받은 메일 10개 

SELECT Z.*  
FROM ( 
        SELECT Y.*, ROWNUM AS RNUM   
        FROM ( 
                  SELECT X.*, B.MAIL_ID , B.MSG_ID, C.SUBJECT, B.MAIL_RECV_DT   
                  FROM (
                        SELECT A.MAILBOX_ID, A.PHYSICAL_NAME  
                        FROM EML_MAILBOX A
                        WHERE EMAIL  = 'sanghyun@naonsoft.com'
                       ) X
                  INNER JOIN EML_MAIL B ON B.MAILBOX_ID = X.MAILBOX_ID      
                  INNER JOIN EML_MAIL_MESSAGE C  ON C.MSG_ID = B.MSG_ID 
                  WHERE X.PHYSICAL_NAME NOT IN ('Sent', 'Spam', 'Drafts', '보낸 편지함', '지운 편지함' )     
                  AND B.MAIL_DEL_YN = 'N' 
                  --AND B.MAIL_RECV_DT BETWEEN TO_DATE('20220101000000', 'YYYYMMDDHH24MISS') AND TO_DATE('20220701235959', 'YYYYMMDDHH24MISS')
                  AND B.MAIL_RECV_DT < TO_DATE('2022-07-01-000000', 'YYYY-MM-DD-HH24MISS') -- 기준 데이터의시간보다 작은 것 
                  AND C.MAIL_FROM LIKE '%barnlife@naonsoft.com%'
                  ORDER BY B.MAIL_RECV_DT  DESC
             ) Y 
    ) Z 
WHERE RNUM < 11      -- 10개만 가져온다. 
     

-- 받은 시간  이전에 받은 메일 10개 
SELECT A.*
FROM (
SELECT Z.*  
FROM ( 
        SELECT Y.*, ROWNUM AS RNUM   
        FROM ( 
                  SELECT X.*, B.MAIL_ID , B.MSG_ID, C.SUBJECT, B.MAIL_RECV_DT   
                  FROM (
                        SELECT A.MAILBOX_ID, A.PHYSICAL_NAME  
                        FROM EML_MAILBOX A
                        WHERE EMAIL  = 'sanghyun@naonsoft.com'
                       ) X
                  INNER JOIN EML_MAIL B ON B.MAILBOX_ID = X.MAILBOX_ID      
                  INNER JOIN EML_MAIL_MESSAGE C  ON C.MSG_ID = B.MSG_ID 
                  WHERE X.PHYSICAL_NAME NOT IN ('Sent', 'Spam', 'Drafts', '보낸 편지함', '지운 편지함' )     
                  AND B.MAIL_DEL_YN = 'N' 
                  --AND B.MAIL_RECV_DT BETWEEN TO_DATE('20220101000000', 'YYYYMMDDHH24MISS') AND TO_DATE('20220701235959', 'YYYYMMDDHH24MISS')
                  AND B.MAIL_RECV_DT > TO_DATE('2022-06-17-000000', 'YYYY-MM-DD-HH24MISS') -- 기준 데이터의시간보다 작은 것 
                  AND C.MAIL_FROM LIKE '%barnlife@naonsoft.com%'
                  ORDER BY B.MAIL_RECV_DT 
             ) Y 
    ) Z 
WHERE RNUM < 11      -- 10개만 가져온다.
   ) A 
ORDER BY A.MAIL_RECV_DT DESC    
     



-- 내가 특정 사용자에게 보낸 메일 
SELECT X.*, B.MAIL_ID , B.MSG_ID, C.SUBJECT, B.MAIL_RECV_DT  
FROM (
      SELECT A.MAILBOX_ID, A.PHYSICAL_NAME  
      FROM EML_MAILBOX A
      WHERE EMAIL  = 'sanghyun@naonsoft.com' /* 나의 메일함 */ 
     ) X
INNER JOIN EML_MAIL B ON B.MAILBOX_ID = X.MAILBOX_ID      
INNER JOIN EML_MAIL_MESSAGE C  ON C.MSG_ID = B.MSG_ID 
INNER JOIN EML_MAIL_TO D ON D.MSG_ID = B.MSG_ID 
WHERE X.PHYSICAL_NAME  IN ('Sent' )     -- 보낸 편지함 
AND B.MAIL_DEL_YN = 'N' 
AND B.MAIL_RECV_DT BETWEEN TO_DATE('20220101000000', 'YYYYMMDDHH24MISS') AND TO_DATE('20220701235959', 'YYYYMMDDHH24MISS') -- 기간 조건 
AND D.MAIL_TO LIKE '%김동규%'   /* mail_to에 대한 데이터 범위 다시 고민 */ 

