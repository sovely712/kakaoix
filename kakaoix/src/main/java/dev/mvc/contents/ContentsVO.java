package dev.mvc.contents;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ContentsVO {

  /** ������ ��ȣ */
  private int contentsno;
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
  
  private MultipartFile filesMF;

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

  public MultipartFile getFilesMF() {
    return filesMF;
  }

  public void setFilesMF(MultipartFile filesMF) {
    this.filesMF = filesMF;
  }
  
  
  
  
  
  
  

}
