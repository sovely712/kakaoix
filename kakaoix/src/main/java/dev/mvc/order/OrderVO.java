package dev.mvc.order;

public class OrderVO {

  /** �Ϸù�ȣ */
  private int orderno;
  /** �ֹ���ȣ */
  private int ordernum;
  /** ��������ȣ */
  private int contentsno;
  /** ��ٱ��Ϲ�ȣ */
  private int basketno;
  /** ȸ����ȣ */
  private int memberno;
  /** ���� */
  private int b_num;
  /** ������ �ݾ�*/
  private int n_price;
  /** ������ ����Ʈ*/
  private int n_point;
  /** ���ݺ� ����Ʈ */
  private int b_point;
  /** ���ֹ��ݾ�*/
  private int tot_price;
  /** ��ۺ� */
  private int dir_price;
  /** ���հ�(���ֹ��ݾ�+��ۺ�) */
  private int totdir_price;
  /** ���� �ݾ�*/
  private int dc_price;
  /** �Ѱ����ݾ�(���ֹ��ݾ�+��ۺ�+���αݾ�)*/
  private int b_price;
  /** �ֹ����� */
  private String odate;
  /** �ֹ�ó������ */
  private String status;
  /** ��� �޴� ��� */
  private String dname;
  /** ��� ���� ��ȣ */
  private String dzipcode; 
  /** ����ּ� */
  private String daddress; 
  /** ��� �޴� ��� �޴��� ��ȣ */
  private String dtel; 
  /** ������޽��� */
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
