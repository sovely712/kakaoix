DROP TABLE MEMBER;
DELETE FROM MEMBER;
SELECT * FROM MEMBER;

CREATE TABLE member (
  memberno  NUMBER(7)     NOT NULL, 
  mid       VARCHAR2(20)  NOT NULL UNIQUE, 
  passwd    VARCHAR2(60)  NOT NULL, 
  mname     VARCHAR2(20)  NOT NULL,
  gender    VARCHAR2(10)  NOT NULL, 
  age       NUMBER(7)     NOT NULL, 
  tel       VARCHAR2(14)  NOT NULL, 
  zipcode   VARCHAR2(5)       NULL, 
  address1  VARCHAR2(80)      NULL, 
  address2  VARCHAR2(50)      NULL, 
  mdate     DATE          NOT NULL,
  PRIMARY KEY (memberno)             
);

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.memberno is '회원 번호';
COMMENT ON COLUMN member.mid is '아이디';
COMMENT ON COLUMN member.passwd is '패스워드';
COMMENT ON COLUMN member.mname is '성명';
COMMENT ON COLUMN member.gender is '성별';
COMMENT ON COLUMN member.age is '나이';
COMMENT ON COLUMN member.tel is '전화번호';
COMMENT ON COLUMN member.zipcode is '우편번호';
COMMENT ON COLUMN member.address1 is '주소1';
COMMENT ON COLUMN member.address2 is '주소2';
COMMENT ON COLUMN member.mdate is '가입일';
