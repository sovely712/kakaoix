DROP TABLE CATEGORY;
DELETE FROM CATEGORY;
SELECT * FROM CATEGORY;

CREATE TABLE category (
categoryno  NUMBER(7)                 NOT NULL,
title       VARCHAR2(50)              NOT NULL,
cdate       DATE                      NOT NULL,
PRIMARY KEY (categoryno)
);

COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.categoryno is '카테고리 번호';
COMMENT ON COLUMN category.title is '게시판 이름';
COMMENT ON COLUMN category.cdate is '등록일';
 
