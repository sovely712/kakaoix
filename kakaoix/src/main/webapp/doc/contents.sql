DROP TABLE CONTENTS;
DELETE FROM CONTENTS;
SELECT * FROM CONTENTS;

CREATE TABLE contents ( 
  contentsno      NUMBER(7)                     NOT NULL, 
  categoryno      NUMBER(7)                     NOT NULL, 
  title           VARCHAR2(200)                 NOT NULL, 
  content         VARCHAR2(4000)                NOT NULL, 
  cnt             NUMBER(7)           DEFAULT 0 NOT NULL, 
  cdate           DATE                          NOT NULL, 
  files           VARCHAR2(1000)                    NULL,
  thumbs          VARCHAR2(100)                     NULL,
  sizes           NUMBER(15)          DEFAULT 0 NOT NULL,
  price           NUMBER(15)                    NOT NULL,
  num             NUMBER(7)                     NOT NULL,
  PRIMARY KEY (contentsno),
  FOREIGN KEY (categoryno) REFERENCES category(categoryno)
  
);

COMMENT ON TABLE contents is '컨텐츠';
COMMENT ON COLUMN contents.contentsno is '컨텐츠번호';
COMMENT ON COLUMN contents.categoryno is '카테고리번호'
COMMENT ON COLUMN contents.title is '제목'
COMMENT ON COLUMN contents.content is '내용'
COMMENT ON COLUMN contents.cnt is '조회수'
COMMENT ON COLUMN contents.cdate is '등록 날짜'
COMMENT ON COLUMN contents.files is '파일'
COMMENT ON COLUMN contents.thumbs is '썸네일'
COMMENT ON COLUMN contents.sizes is '파일 사이즈'
COMMENT ON COLUMN contents.price is '가격'
COMMENT ON COLUMN contents.num is '재고량'

