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

COMMENT ON TABLE point is '적립금';
COMMENT ON COLUMN point.pointno is '적립금번호';
COMMENT ON COLUMN point.u_point is '사용적립금';
COMMENT ON COLUMN point.s_point is '적립금';
COMMENT ON COLUMN point.pstatus is '적립금 사용여부'; /*적립금 사용 U 미사용S*/  
COMMENT ON COLUMN point.pdate is '적립 날짜';
COMMENT ON COLUMN point.memberno is '회원번호';
COMMENT ON COLUMN point.orderno is '주문번호';

