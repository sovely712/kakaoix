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

COMMENT ON TABLE contents is '������';
COMMENT ON COLUMN contents.contentsno is '��������ȣ';
COMMENT ON COLUMN contents.categoryno is 'ī�װ���ȣ'
COMMENT ON COLUMN contents.title is '����'
COMMENT ON COLUMN contents.content is '����'
COMMENT ON COLUMN contents.cnt is '��ȸ��'
COMMENT ON COLUMN contents.cdate is '��� ��¥'
COMMENT ON COLUMN contents.files is '����'
COMMENT ON COLUMN contents.thumbs is '�����'
COMMENT ON COLUMN contents.sizes is '���� ������'
COMMENT ON COLUMN contents.price is '����'
COMMENT ON COLUMN contents.num is '���'

