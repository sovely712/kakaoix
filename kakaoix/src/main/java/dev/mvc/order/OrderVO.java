package dev.mvc.order;

public class OrderVO {

  /** 일련번호 */
  private int orderno;
  /** 주문번호 */
  private int ordernum;
  /** 컨텐츠번호 */
  private int contentsno;
  /** 장바구니번호 */
  private int basketno;
  /** 회원번호 */
  private int memberno;
  /** 수량 */
  private int b_num;
  /** 수량별 금액*/
  private int n_price;
  /** 수량별 포인트*/
  private int n_point;
  /** 가격별 포인트 */
  private int b_point;
  /** 총주문금액*/
  private int tot_price;
  /** 배송비 */
  private int dir_price;
  /** 총합계(총주문금액+배송비) */
  private int totdir_price;
  /** 할인 금액*/
  private int dc_price;
  /** 총결제금액(총주문금액+배송비+할인금액)*/
  private int b_price;
  /** 주문일자 */
  private String odate;
  /** 주문처리상태 */
  private String status;
  /** 배송 받는 사람 */
  private String dname;
  /** 배송 우편 번호 */
  private String dzipcode; 
  /** 배송주소 */
  private String daddress; 
  /** 배송 받는 사람 휴대폰 번호 */
  private String dtel; 
  /** 배송지메시지 */
  private String dcontent; 
  
  
  public OrderVO(){
    
  }


  public int getOrderno() {
    return orderno;
  }


  public void setOrderno(int orderno) {
    this.orderno = orderno;
  }


  public int getOrdernum() {
    return ordernum;
  }


  public void setOrdernum(int ordernum) {
    this.ordernum = ordernum;
  }


  public int getContentsno() {
    return contentsno;
  }


  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }


  public int getBasketno() {
    return basketno;
  }


  public void setBasketno(int basketno) {
    this.basketno = basketno;
  }


  public int getMemberno() {
    return memberno;
  }


  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }


  public int getB_num() {
    return b_num;
  }


  public void setB_num(int b_num) {
    this.b_num = b_num;
  }


  public int getN_price() {
    return n_price;
  }


  public void setN_price(int n_price) {
    this.n_price = n_price;
  }


  public int getN_point() {
    return n_point;
  }


  public void setN_point(int n_point) {
    this.n_point = n_point;
  }


  public int getB_point() {
    return b_point;
  }


  public void setB_point(int b_point) {
    this.b_point = b_point;
  }


  public int getTot_price() {
    return tot_price;
  }


  public void setTot_price(int tot_price) {
    this.tot_price = tot_price;
  }


  public int getDir_price() {
    return dir_price;
  }


  public void setDir_price(int dir_price) {
    this.dir_price = dir_price;
  }


  public int getTotdir_price() {
    return totdir_price;
  }


  public void setTotdir_price(int totdir_price) {
    this.totdir_price = totdir_price;
  }


  public int getDc_price() {
    return dc_price;
  }


  public void setDc_price(int dc_price) {
    this.dc_price = dc_price;
  }


  public int getB_price() {
    return b_price;
  }


  public void setB_price(int b_price) {
    this.b_price = b_price;
  }


  public String getOdate() {
    return odate;
  }


  public void setOdate(String odate) {
    this.odate = odate;
  }


  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  public String getDname() {
    return dname;
  }


  public void setDname(String dname) {
    this.dname = dname;
  }


  public String getDzipcode() {
    return dzipcode;
  }


  public void setDzipcode(String dzipcode) {
    this.dzipcode = dzipcode;
  }


  public String getDaddress() {
    return daddress;
  }


  public void setDaddress(String daddress) {
    this.daddress = daddress;
  }


  public String getDtel() {
    return dtel;
  }


  public void setDtel(String dtel) {
    this.dtel = dtel;
  }


  public String getDcontent() {
    return dcontent;
  }


  public void setDcontent(String dcontent) {
    this.dcontent = dcontent;
  }


 
  
  
}
