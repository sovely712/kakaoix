package dev.mvc.basket;

public class Contents_BasketVO {
  
  //basket
  /** ��ٱ��� ��ȣ*/
  private int basketno;
  /** ���� */
  private int b_num;
  /** ����� */
  private char visible;
  /** ȸ�� ��ȣ */
  private int memberno;
  /** ������ ��ȣ */
  private int contentsno;
  
  //contents
  /** ī�װ� ��ȣ */
  private int categoryno;
  /** ������ ��ȸ�� */
  private int cnt =0;
  /** ��ǰ ���� */
  private int price;
  /** ��� */
  private int num;
  /** ���� ������ */
  private int sizes =0;
  /** ���� */
  private String files ="";
  /** ����� */
  private String thumbs ="";
  /** ��ǰ�� */
  private String title;
  /** ������ ���� */
  private String content;
  /** ������ ����� */
  private String cdate;
  /** ������ �˻��� */
  private String word;
  
  //member
  /** ȸ�� ���̵�*/
  private String mid; 
  /** ȸ�� ���*/
  private String passwd;
  /** ȸ�� �̸� */
  private String mname;
  /** ȸ�� ���� */
  private String gender;
  /** ȸ�� ���� */
  private int age;
  /** ȸ�� ��ȭ��ȣ */
  private String tel;
  /** ȸ�� �����ȣ */
  private String zipcode;
  /** ȸ�� �ּ�1 */
  private String address1;
  /** ȸ�� �ּ�2*/
  private String address2;
  /** ȸ�� ������ */
  private String mdate; 
  
  public Contents_BasketVO(){
    
  }

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

  public int getContentsno() {
    return contentsno;
  }

  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }

  public int getCategoryno() {
    return categoryno;
  }

  public void setCategoryno(int categoryno) {
    this.categoryno = categoryno;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public int getSizes() {
    return sizes;
  }

  public void setSizes(int sizes) {
    this.sizes = sizes;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }

  public String getThumbs() {
    return thumbs;
  }

  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCdate() {
    return cdate;
  }

  public void setCdate(String cdate) {
    this.cdate = cdate;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getMdate() {
    return mdate;
  }

  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  
  
  
  

}
