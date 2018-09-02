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
 

COMMENT ON TABLE kakaoix_order is '�ֹ�';
COMMENT ON COLUMN kakaoix_order.orderno is '�Ϸù�ȣ';
COMMENT ON COLUMN kakaoix_order.ordernum is '�ֹ���ȣ';
COMMENT ON COLUMN kakaoix_order.contentsno is '��������ȣ';
COMMENT ON COLUMN kakaoix_order.basketno is '��ٱ��Ϲ�ȣ';
COMMENT ON COLUMN kakaoix_order.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN kakaoix_order.b_num is '����';
COMMENT ON COLUMN kakaoix_order.n_price is '������ �ݾ�';
COMMENT ON COLUMN kakaoix_order.n_point is '���ݺ� ����Ʈ';
COMMENT ON COLUMN kakaoix_order.b_point is '���հ�����Ʈ';
COMMENT ON COLUMN kakaoix_order.tot_price is '���ֹ��ݾ�';
COMMENT ON COLUMN kakaoix_order.dir_price is '��ۺ�';
COMMENT ON COLUMN kakaoix_order.totdir_price is '���ֹ��ݾ�+��ۺ�';
COMMENT ON COLUMN kakaoix_order.dc_price is '���αݾ�';
COMMENT ON COLUMN kakaoix_order.b_price is '�Ѱ����ݾ�';
COMMENT ON COLUMN kakaoix_order.status is '�ֹ�ó������';
COMMENT ON COLUMN kakaoix_order.odate is '�ֹ���¥';
COMMENT ON COLUMN kakaoix_order.dname is '��� �޴� ���';
COMMENT ON COLUMN kakaoix_order.dzipcode is '��� ���� ��ȣ';
COMMENT ON COLUMN kakaoix_order.daddress is '����ּ�';
COMMENT ON COLUMN kakaoix_order.dtel is '��� �޴� ��� �޴��� ��ȣ';
COMMENT ON COLUMN kakaoix_order.dcontent is '��۸޽���';

