DROP TABLE CATEGORY;
DELETE FROM CATEGORY;
SELECT * FROM CATEGORY;

CREATE TABLE category (
categoryno  NUMBER(7)                 NOT NULL,
title       VARCHAR2(50)              NOT NULL,
cdate       DATE                      NOT NULL,
PRIMARY KEY (categoryno)
);

COMMENT ON TABLE category is 'ī�װ�';
COMMENT ON COLUMN category.categoryno is 'ī�װ� ��ȣ';
COMMENT ON COLUMN category.title is '�Խ��� �̸�';
COMMENT ON COLUMN category.cdate is '�����';
 
