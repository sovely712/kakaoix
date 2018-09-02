package dev.mvc.order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.order.OrderProc")
public class OrderProc implements OrderProcInter {

  @Autowired
  @Qualifier("dev.mvc.order.OrderDAO") 
  private OrderDAOInter orderDAO = null;
  
  public OrderProc(){
    System.out.println("-->OrderProc created.");
  }

  @Override
  public int create(OrderVO orderVO) {
    int count = orderDAO.create(orderVO);
    return count;
  }
  
  @Override
  public int ordernum() {
    int count = orderDAO.ordernum();
    return count;
  }
  
  @Override
  public OrderVO read(int orderno) {
    OrderVO orderVO = orderDAO.read(orderno);
    return orderVO;
  }
  
  @Override
  public List<Order_JoinVO> List_read(int ordernum) {
    List<Order_JoinVO> list = orderDAO.List_read(ordernum); 
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    int cnt = orderDAO.search_count(hashMap);
    return cnt;
  }

  @Override
  public String paging(int memberno, int search_count, int nowPage, String status) {
    int totalPage = (int)(Math.ceil((double)search_count/Order.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Order.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Order.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Order.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Order.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    int _nowPage = (nowGrp-1) * Order.PAGE_PER_BLOCK; 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_order_search_paging.do?&status="+status+"&nowPage="+_nowPage+"&memberno="+memberno+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list_order_search_paging.do?status="+status+"&nowPage="+i+"&memberno="+memberno+"'>"+i+"</A></span>");   
      } 
    } 
     
    //10�� ���� �������� �̵� 
    //nowGrp:1 (1~10page), nowGrp:2 (11~20page), nowGrp:3 (21~30page),
    //���� 1�׷��� ��� : (1*10)+1 2�׷��� 11
    //���� 2�׷��� ��� : (2*10)+1 2�׷��� 21
    _nowPage = (nowGrp * Order.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_order_search_paging.do?&status="+status+"&nowPage="+_nowPage+"&memberno="+memberno+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  @Override
  public List<Order_JoinVO> list_order_search_paging(HashMap hashMap) {
  
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Order.RECORD_PER_PAGE;
   int startNum = beginOfPage + 1; 
   int endNum = beginOfPage + Order.RECORD_PER_PAGE;   
 
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<Order_JoinVO> list = orderDAO.list_order_search_paging(hashMap); 
   Iterator<Order_JoinVO> iter = list.iterator();
   
   return list;
  }

  @Override
  public int updateStatus(OrderVO orderVO) {
    int count = orderDAO.updateStatus(orderVO);
    return count;
  }
  
  @Override
  public int updatePrice(OrderVO orderVO) {
    int count = orderDAO.updatePrice(orderVO);
    return count;
  }
  


}
