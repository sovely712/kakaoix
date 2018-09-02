package dev.mvc.category;

public class CategoryVO {
  
  /** 카테고리 번호 */
  private int categoryno;
  /** 카테고리 이름 */
  private String title;
  /** 카테고리 등록일 */
  private String cdate;
  
  public CategoryVO() {

  }

  public int getCategoryno() {
    return categoryno;
  }

  public void setCategoryno(int categoryno) {
    this.categoryno = categoryno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCdate() {
    return cdate;
  }

  public void setCdate(String cdate) {
    this.cdate = cdate;
  }

  
  
    
}
 