package dev.mvc.contents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.category.CategoryVO;
import dev.mvc.tool.Tool;

@Component("dev.mvc.contents.ContentsProc")
public class ContentsProc implements ContentsProcInter{

  @Autowired
  @Qualifier("dev.mvc.contents.ContentsDAO")
  private ContentsDAOInter contentsDAO = null;
  
  public ContentsProc() {
    System.out.println("--> ContentsProc created.");
  }

  @Override
  public int create(ContentsVO contentsVO) {
    int count = contentsDAO.create(contentsVO);
    return count;
  }

  @Override
  public int search_count(HashMap hashMap) {
    int cnt = contentsDAO.search_count(hashMap);
    return cnt;
  }
  
  @Override
  public List<ContentsVO> list_search(HashMap hashMap) {
    /* 
     ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
     1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
     2 ������: nowPage = 2, (2 - 1) * 10 --> 10
     3 ������: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Contents.RECORD_PER_PAGE;
    
     // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
    int startNum = beginOfPage + 1; 
    //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
    int endNum = beginOfPage + Contents.RECORD_PER_PAGE;   
    /*
     1 ������: WHERE r >= 1 AND r <= 10
     2 ������: WHERE r >= 11 AND r <= 20
     3 ������: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<ContentsVO> list = contentsDAO.list_search(hashMap); 
    Iterator<ContentsVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      ContentsVO contentsVO = iter.next();
      String title = Tool.textLength(contentsVO.getTitle(), 90);
      title = Tool.convertChar(title); // �±� ó��
      contentsVO.setTitle(title);
      
      String thumbs = contentsVO.getThumbs();
      
      if (thumbs != null) {
        if (thumbs.length() > 0) {                // preview �̹����� �ִ��� �˻�
           String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
           contentsVO.setThumbs(thumb);
        }
      }
    }
    return list;
  }

  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param category     ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage      ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word){ 
    int totalPage = (int)(Math.ceil((double)search_count/Contents.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Contents.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Contents.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Contents.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Contents.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:#cccccc; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:#cccccc; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:#cccccc; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 2px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #35221a;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 2px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #35221a;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
   //str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    // ���� 10�� �������� �̵� 
    //nowGrp:1 (1~10page), nowGrp:2 (11~20page), nowGrp:3 (21~30page),
    //(2-1)*10 = 1�׷��� 10
    //(3-1)*10 = 2�׷��� 20(�Ǹ�����)
    int _nowPage = (nowGrp-1) * Contents.PAGE_PER_BLOCK; 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    //10�� ���� �������� �̵� 
    //nowGrp:1 (1~10page), nowGrp:2 (11~20page), nowGrp:3 (21~30page),
    //���� 1�׷��� ��� : (1*10)+1 2�׷��� 11
    //���� 2�׷��� ��� : (2*10)+1 2�׷��� 21
    _nowPage = (nowGrp * Contents.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  @Override
  public ContentsVO read(int contentsno) {
    ContentsVO contentsVO = contentsDAO.read(contentsno);
    return contentsVO;
  }
  
  @Override
  public int increaseCnt(int contentsno) {
    int count = contentsDAO.increaseCnt(contentsno);
    return count;
  }

  @Override
  public int decreaseNum(int contentsno) {
    int count = contentsDAO.decreaseNum(contentsno);
    return count;
  }


  
}
