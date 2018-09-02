package dev.mvc.member;

public class MemberVO {
  
  /** 회원 번호 */
  private int memberno;
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
  
  /** 등록된 패스워드 */
  private String old_passwd = "";
  /** id 저장 여부 */
  private String mid_save = "";
  /** passwd 저장 여부 */
  private String passwd_save = "";
  /** 이동할 주소 저장 */
  private String url_address = "";

  
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
  
  public String getOld_passwd() {
    return old_passwd;
  }
  public void setOld_passwd(String old_passwd) {
    this.old_passwd = old_passwd;
  }

  public String getMid_save() {
    return mid_save;
  }
  public void setMid_save(String mid_save) {
    this.mid_save = mid_save;
  }

  public String getPasswd_save() {
    return passwd_save;
  }
  public void setPasswd_save(String passwd_save) {
    this.passwd_save = passwd_save;
  }

  public String getUrl_address() {
    return url_address;
  }
  public void setUrl_address(String url_address) {
    this.url_address = url_address;
  }
  
}
