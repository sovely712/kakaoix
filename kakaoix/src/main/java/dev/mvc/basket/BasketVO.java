package dev.mvc.basket;

public class BasketVO {
  
  /** 장바구니 번호*/
  private int basketno;
  /** 수량 */
  private int b_num;
  /** 숨기기 */
  private char visible;
  /** 회원 번호 */
  private int memberno;
  /** 컨텐츠 번호 */
  private int contentsno;
  
  public BasketVO(){}

  public int getBasketno() {
    return basketno;
  }

  public void setBasketno(int basketno) {
    this.basketno = basketno;
  }

  public int getB_num() {
    return b_num;
  }

  public void setB_num(int b_num) {
    this.b_num = b_num;
  }

  public char getVisible() {
    return visible;
  }

  public void setVisible(char visible) {
    this.visible = visible;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public int getContentsno() {
    return contentsno;
  }

  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }
  
  
  


}
