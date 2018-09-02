DROP TABLE POINT;
DELETE FROM POINT;
SELECT * FROM POINT;

CREATE TABLE point(
    pointno                NUMBER(7)     NOT NULL,     
    u_point                NUMBER(7)     DEFAULT '0' NULL,
    s_point                NUMBER(7)     DEFAULT '0' NULL,
    pstatus                CHAR(1)       DEFAULT 'S' NOT NULL, 
    pdate                  DATE          NOT NULL, 
    memberno               NUMBER(7)     DEFAULT '0'  NOT NULL,
    orderno                NUMBER(7)     DEFAULT '0'  NOT NULL,
    PRIMARY KEY (pointno), 
    FOREIGN KEY (memberno) REFERENCES member(memberno),
    FOREIGN KEY (orderno) REFERENCES kakaoix_order(orderno)
);

COMMENT ON TABLE point is '������';
COMMENT ON COLUMN point.pointno is '�����ݹ�ȣ';
COMMENT ON COLUMN point.u_point is '���������';
COMMENT ON COLUMN point.s_point is '������';
COMMENT ON COLUMN point.pstatus is '������ ��뿩��'; /*������ ��� U �̻��S*/  
COMMENT ON COLUMN point.pdate is '���� ��¥';
COMMENT ON COLUMN point.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN point.orderno is '�ֹ���ȣ';

