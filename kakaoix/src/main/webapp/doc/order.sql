DROP TABLE KAKAOIX_ORDER;
DELETE FROM KAKAOIX_ORDER;
SELECT * FROM KAKAOIX_ORDER;

CREATE TABLE kakaoix_order(
  orderno      NUMBER(7)                       NOT NULL,
  ordernum     NUMBER(7)                       NOT NULL,
  contentsno   NUMBER(7)     DEFAULT '0'       NOT NULL,
  basketno     NUMBER(7)     DEFAULT '0'       NOT NULL,
  memberno     NUMBER(7)     DEFAULT '0'       NOT NULL,
  b_num        NUMBER(15)                      NULL,
  n_price      NUMBER(15)                      NULL,
  n_point      NUMBER(15)                      NULL,
  b_point      NUMBER(15)                      NULL,
  tot_price    NUMBER(15)                      NULL,
  dir_price    NUMBER(15)                      NULL,
  totdir_price NUMBER(15)                      NULL,
  dc_price     NUMBER(15)                      NULL,
  b_price      NUMBER(15)                      NULL,
  status       VARCHAR2(20)  DEFAULT '1'       NOT NULL,
  odate        DATE                            NOT NULL,
  dname        VARCHAR2(20)                    NOT NULL,
  dzipcode     VARCHAR2(5)                    NOT NULL,
  daddress     VARCHAR2(100)                   NOT NULL,
  dtel         VARCHAR2(14)                    NOT NULL,
  dcontent     VARCHAR2(100)                   NULL,
  PRIMARY KEY(orderno),
  FOREIGN KEY(contentsno) REFERENCES contents(contentsno),
  FOREIGN KEY(basketno) REFERENCES basket(basketno),
  FOREIGN KEY(memberno) REFERENCES member(memberno)
);
 

COMMENT ON TABLE kakaoix_order is '주문';
COMMENT ON COLUMN kakaoix_order.orderno is '일련번호';
COMMENT ON COLUMN kakaoix_order.ordernum is '주문번호';
COMMENT ON COLUMN kakaoix_order.contentsno is '컨텐츠번호';
COMMENT ON COLUMN kakaoix_order.basketno is '장바구니번호';
COMMENT ON COLUMN kakaoix_order.memberno is '회원번호';
COMMENT ON COLUMN kakaoix_order.b_num is '수량';
COMMENT ON COLUMN kakaoix_order.n_price is '수량별 금액';
COMMENT ON COLUMN kakaoix_order.n_point is '가격별 포인트';
COMMENT ON COLUMN kakaoix_order.b_point is '총합계포인트';
COMMENT ON COLUMN kakaoix_order.tot_price is '총주문금액';
COMMENT ON COLUMN kakaoix_order.dir_price is '배송비';
COMMENT ON COLUMN kakaoix_order.totdir_price is '총주문금액+배송비';
COMMENT ON COLUMN kakaoix_order.dc_price is '할인금액';
COMMENT ON COLUMN kakaoix_order.b_price is '총결제금액';
COMMENT ON COLUMN kakaoix_order.status is '주문처리상태';
COMMENT ON COLUMN kakaoix_order.odate is '주문날짜';
COMMENT ON COLUMN kakaoix_order.dname is '배송 받는 사람';
COMMENT ON COLUMN kakaoix_order.dzipcode is '배송 우편 번호';
COMMENT ON COLUMN kakaoix_order.daddress is '배송주소';
COMMENT ON COLUMN kakaoix_order.dtel is '배송 받는 사람 휴대폰 번호';
COMMENT ON COLUMN kakaoix_order.dcontent is '배송메시지';

