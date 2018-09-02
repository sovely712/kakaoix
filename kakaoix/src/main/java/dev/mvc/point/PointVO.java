package dev.mvc.point;

public class PointVO {
  /** 포인트 일련번호 */
  private int pointno;
  /** 사용 포인트 */
  private int u_point;
  /** 적립 포인트 */
  private int s_point;
  /** 사용 여부 S:사용 안함 U:사용함 */
  private char pstatus;
  /** 포인트 적립일*/
  private String pdate;
  /** 회원 번호*/
  private int memberno;
  /** 주문 일련번호*/
  private int orderno;
  
  public PointVO(){
    
  }
  
  public int getU_point() {
    return u_point;
  }


  public void setU_point(int u_point) {
    this.u_point = u_point;
  }


  public int getS_point() {
    return s_point;
  }


  public void setS_point(int s_point) {
    this.s_point = s_point;
  }


  public int getPointno() {
    return pointno;
  }
  public void setPointno(int pointno) {
    this.pointno = pointno;
  }
  public char getPstatus() {
    return pstatus;
  }
  public void setPstatus(char pstatus) {
    this.pstatus = pstatus;
  }
  
  public String getPdate() {
    return pdate;
  }
  public void setPdate(String pdate) {
    this.pdate = pdate;
  }

  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getOrderno() {
    return orderno;
  }
  public void setOrderno(int orderno) {
    this.orderno = orderno;
  }

 
  
  
  

}
