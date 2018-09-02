DROP TABLE BASKET;
DELETE FROM BASKET;
SELECT * FROM BASKET;

CREATE TABLE basket(
    basketno                NUMBER(7)                NOT NULL,     
    b_num                   NUMBER(7)                NOT NULL,
    visible                 CHAR(1)     DEFAULT 'Y'  NOT NULL,
    memberno                NUMBER(7)   DEFAULT '0'  NOT NULL,
    contentsno              NUMBER(7)   DEFAULT '0'  NOT NULL,
    PRIMARY KEY (basketno), 
    FOREIGN KEY (memberno) REFERENCES member(memberno),
    FOREIGN KEY (contentsno) REFERENCES contents(contentsno)
);

COMMENT ON TABLE basket is '장바구니';
COMMENT ON COLUMN basket.basketno is '장바구니 번호';
COMMENT ON COLUMN basket.b_num is '상품 수량';
COMMENT ON COLUMN basket.visible is '상태 변경';
COMMENT ON COLUMN basket.memberno is '회원 번호';
COMMENT ON COLUMN basket.contentsno is '컨텐츠 번호';

INSERT INTO basket(basketno, b_num, visible, contentsno, memberno)
VALUES(0, 1, 'Y',1, 1);
