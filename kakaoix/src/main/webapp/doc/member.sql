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

COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN member.mid is '���̵�';
COMMENT ON COLUMN member.passwd is '�н�����';
COMMENT ON COLUMN member.mname is '����';
COMMENT ON COLUMN member.gender is '����';
COMMENT ON COLUMN member.age is '����';
COMMENT ON COLUMN member.tel is '��ȭ��ȣ';
COMMENT ON COLUMN member.zipcode is '�����ȣ';
COMMENT ON COLUMN member.address1 is '�ּ�1';
COMMENT ON COLUMN member.address2 is '�ּ�2';
COMMENT ON COLUMN member.mdate is '������';
