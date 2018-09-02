package dev.mvc.basket;

public class Contents_BasketVO {
  
  //basket
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
  
  //contents
  /** 카테고리 번호 */
  private int categoryno;
  /** 컨텐츠 조회수 */
  private int cnt =0;
  /** 상품 가격 */
  private int price;
  /** 재고량 */
  private int num;
  /** 파일 사이즈 */
  private int sizes =0;
  /** 파일 */
  private String files ="";
  /** 썸네일 */
  private String thumbs ="";
  /** 상품명 */
  private String title;
  /** 컨텐츠 내용 */
  private String content;
  /** 컨텐츠 등록일 */
  private String cdate;
  /** 컨텐츠 검색어 */
  private String word;
  
  //member
  /** 회원 아이디*/
  private String mid; 
  /** 회원 비번*/
  private String passwd;
  /** 회원 이름 */
  private String mname;
  /** 회원 성별 */
  private String gender;
  /** 회원 나이 */
  private int age;
  /** 회원 전화번호 */
  private String tel;
  /** 회원 우편번호 */
  private String zipcode;
  /** 회원 주소1 */
  private String address1;
  /** 회원 주소2*/
  private String address2;
  /** 회원 가입일 */
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
