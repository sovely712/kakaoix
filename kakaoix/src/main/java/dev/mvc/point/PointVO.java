package dev.mvc.point;

public class PointVO {
  /** ����Ʈ �Ϸù�ȣ */
  private int pointno;
  /** ��� ����Ʈ */
  private int u_point;
  /** ���� ����Ʈ */
  private int s_point;
  /** ��� ���� S:��� ���� U:����� */
  private char pstatus;
  /** ����Ʈ ������*/
  private String pdate;
  /** ȸ�� ��ȣ*/
  private int memberno;
  /** �ֹ� �Ϸù�ȣ*/
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
