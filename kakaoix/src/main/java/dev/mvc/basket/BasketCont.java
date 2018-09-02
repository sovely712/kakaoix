package dev.mvc.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;


@Controller
public class BasketCont {

  @Autowired
  @Qualifier("dev.mvc.basket.BasketProc")
  private BasketProcInter basketProc;
  
  public BasketCont(){
    System.out.println("--> BasketCont created.");
    
  }
  
  /**
   * ��ٱ��Ͽ� ���
   */
  @RequestMapping(value="/basket/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/basket/create"); // /basket/create.jsp
    
    return mav;
  }
 
  @RequestMapping(value="/basket/create.do", method=RequestMethod.POST)
  public ModelAndView create(BasketVO basketVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (Tool.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    }else{
      
      int memberno = (Integer)session.getAttribute("memberno");
      basketVO.setMemberno(memberno);
      
      int count = basketProc.create(basketVO);
      
      if (count== 1) {
        mav.setViewName("redirect:/basket/list.do");
      } else {
        msgs.add("��ٱ��� ��Ͽ� �����߽��ϴ�.");
        msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� �� ����: 000-0000-0000");
        links.add("<button type='button' onclick=\"history.back();\">�ٽ� �õ�</button>");
        links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
   
        mav.addObject("msgs", msgs);
        mav.addObject("links", links);
        
        mav.setViewName("/basket/message"); 
      }
      
    }
 
    return mav;
  }

  /**
   * ������ �����Ͽ� ��ǰ ���
   */
  @RequestMapping(value="/basket/many_create.do", method=RequestMethod.POST)
  public ModelAndView many_create_book(String checkboxValues, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (Tool.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    }else{
  
      int memberno = (Integer)session.getAttribute("memberno");
      
      String[] booksplit = null;
      int[] count = null;
  
      if(checkboxValues.length() != 0){ // 1���̻� üũ������
        booksplit= checkboxValues.split("/");
          int[] contentsnos = new int[booksplit.length];
          count = new int[booksplit.length];
          
          for(int i=0;i<booksplit.length;i++){
            contentsnos[i] = Integer.parseInt(booksplit[i]);
            System.out.println("contentsnos["+i+"]:" + contentsnos[i]);
            
            BasketVO basketVO = new BasketVO();
            basketVO.setB_num(1);
            basketVO.setVisible('Y');
            basketVO.setMemberno(memberno);
            basketVO.setContentsno(contentsnos[i]);
                   
            count[i] = basketProc.create(basketVO);
          }
      }else{ // �ƹ��͵� üũ�����ʾ�����
          System.out.println("�ƹ��͵� üũ�����ʾ�����");
      }
      
      mav.setViewName("redirect:/basket/list.do");
    }
    return mav;
  }
  
  /**
   * ��ٱ��� ����ó��
   */
  @RequestMapping(value = "/basket/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int basketno) {  
  
    ModelAndView mav = new ModelAndView();
  
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    Contents_BasketVO contents_basketVO = basketProc.read(basketno);
    
    //delete ��� visible�� �Ⱥ��̰� �Ѵ�.�ֹ����̺� basketno�� ���⶧����!!
    contents_basketVO.setVisible('N');
    
    int count = basketProc.visible(contents_basketVO);
    
    if (count == 1) {
      mav.setViewName("redirect:/basket/list.do");     
    } else {
      msgs.add("ī�װ� ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� ���� ���: 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");
  
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
  
      mav.setViewName("/basket/message"); 
    }
  
    return mav;
  }
  
  /**
   * ��ٱ��� ������ ����ó��
   */
  @RequestMapping(value = "/basket/many_delete.do", method = RequestMethod.POST)
  public ModelAndView delete(String checkboxValues, HttpSession session) {  
  
    ModelAndView mav = new ModelAndView();
  
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String[] basketsplit = null;
    int[] count = null;

    if(checkboxValues.length() != 0){ // 1���̻� üũ������
      basketsplit= checkboxValues.split("/");
        int[] basketnos = new int[basketsplit.length];
        
        count = new int[basketsplit.length];
             
        for(int i=0;i<basketsplit.length;i++){
          basketnos[i] = Integer.parseInt(basketsplit[i]);
          System.out.println("basketnos["+i+"]:" + basketnos[i]);
               
          Contents_BasketVO contents_basketVO = basketProc.read(basketnos[i]);
          
          //delete ��� visible�� �Ⱥ��̰� �Ѵ�. �ֹ����̺� basketno�� ���� ������!!
          contents_basketVO.setVisible('N');
          basketProc.visible(contents_basketVO);

        }
      
    }else{ // �ƹ��͵� üũ�����ʾ�����
        System.out.println("�ƹ��͵� üũ�����ʾ�����");
    }
    
    mav.setViewName("redirect:/basket/list.do");  
    
    return mav;
  }
  
  /**
   * ȸ��
   * ��ٱ��� ���
   */
  @RequestMapping(value="/basket/list.do", method=RequestMethod.GET)
  public ModelAndView list_book(HttpSession session) {
    
    ModelAndView mav = new ModelAndView();
    
    if (Tool.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    }else{
    
      int memberno = (Integer)session.getAttribute("memberno");
      
      List<Contents_BasketVO> list = basketProc.list(memberno);
      
      //��ǰ ����
      int cnt = basketProc.count(memberno);
      
      mav.addObject("list", list);
      mav.addObject("cnt", cnt);
      mav.setViewName("/basket/list"); 
    }
      
    return mav;
  }

  /**
   * ��ٱ��Ͽ��� ������ �����ؼ�
   * ������ ��ǰ�� ������ ������ ��ǰ������ ��
   */
  @ResponseBody
  @RequestMapping(value="/basket/check.do", 
                            method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String check_book(String checkboxValues) {
    
    String[] basketsplit = null;
    int sum = 0;
    int cnt = 0;
    
    if(checkboxValues.length() != 0){ // 1���̻� üũ������
        basketsplit= checkboxValues.split("/");
        int[] basketnos = new int[basketsplit.length];
        cnt = basketsplit.length;
        
        for(int i=0;i<basketsplit.length;i++){
          basketnos[i] = Integer.parseInt(basketsplit[i]);
          //System.out.println("basketnos["+i+"]:" + basketnos[i]);
          
          Contents_BasketVO contents_basketVO = basketProc.read(basketnos[i]);
          sum += (contents_basketVO.getPrice() * contents_basketVO.getB_num());
        }
      
    }else{ // �ƹ��͵� üũ�����ʾ�����
        System.out.println("�ƹ��͵� üũ�����ʾ�����");
        sum = 0;
        cnt = 0;
    }
    
    JSONObject obj = new JSONObject();
    obj.put("sum", sum);
    obj.put("cnt", cnt);

    return obj.toJSONString();
  }
  
   
}