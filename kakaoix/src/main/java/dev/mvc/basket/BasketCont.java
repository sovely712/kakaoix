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
   * 장바구니에 등록
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
        msgs.add("장바구니 등록에 실패했습니다.");
        msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 고객 센터: 000-0000-0000");
        links.add("<button type='button' onclick=\"history.back();\">다시 시도</button>");
        links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
   
        mav.addObject("msgs", msgs);
        mav.addObject("links", links);
        
        mav.setViewName("/basket/message"); 
      }
      
    }
 
    return mav;
  }

  /**
   * 여러개 선택하여 상품 등록
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
  
      if(checkboxValues.length() != 0){ // 1개이상 체크했을때
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
      }else{ // 아무것도 체크하지않았을때
          System.out.println("아무것도 체크하지않았을때");
      }
      
      mav.setViewName("redirect:/basket/list.do");
    }
    return mav;
  }
  
  /**
   * 장바구니 삭제처리
   */
  @RequestMapping(value = "/basket/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int basketno) {  
  
    ModelAndView mav = new ModelAndView();
  
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    Contents_BasketVO contents_basketVO = basketProc.read(basketno);
    
    //delete 대신 visible로 안보이게 한다.주문테이블에 basketno가 들어가기때문에!!
    contents_basketVO.setVisible('N');
    
    int count = basketProc.visible(contents_basketVO);
    
    if (count == 1) {
      mav.setViewName("redirect:/basket/list.do");     
    } else {
      msgs.add("카테고리 삭제에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 전산 운영팀: 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
  
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
  
      mav.setViewName("/basket/message"); 
    }
  
    return mav;
  }
  
  /**
   * 장바구니 여러개 삭제처리
   */
  @RequestMapping(value = "/basket/many_delete.do", method = RequestMethod.POST)
  public ModelAndView delete(String checkboxValues, HttpSession session) {  
  
    ModelAndView mav = new ModelAndView();
  
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String[] basketsplit = null;
    int[] count = null;

    if(checkboxValues.length() != 0){ // 1개이상 체크했을때
      basketsplit= checkboxValues.split("/");
        int[] basketnos = new int[basketsplit.length];
        
        count = new int[basketsplit.length];
             
        for(int i=0;i<basketsplit.length;i++){
          basketnos[i] = Integer.parseInt(basketsplit[i]);
          System.out.println("basketnos["+i+"]:" + basketnos[i]);
               
          Contents_BasketVO contents_basketVO = basketProc.read(basketnos[i]);
          
          //delete 대신 visible로 안보이게 한다. 주문테이블에 basketno가 들어가기 때문에!!
          contents_basketVO.setVisible('N');
          basketProc.visible(contents_basketVO);

        }
      
    }else{ // 아무것도 체크하지않았을때
        System.out.println("아무것도 체크하지않았을때");
    }
    
    mav.setViewName("redirect:/basket/list.do");  
    
    return mav;
  }
  
  /**
   * 회원
   * 장바구니 목록
   */
  @RequestMapping(value="/basket/list.do", method=RequestMethod.GET)
  public ModelAndView list_book(HttpSession session) {
    
    ModelAndView mav = new ModelAndView();
    
    if (Tool.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    }else{
    
      int memberno = (Integer)session.getAttribute("memberno");
      
      List<Contents_BasketVO> list = basketProc.list(memberno);
      
      //상품 갯수
      int cnt = basketProc.count(memberno);
      
      mav.addObject("list", list);
      mav.addObject("cnt", cnt);
      mav.setViewName("/basket/list"); 
    }
      
    return mav;
  }

  /**
   * 장바구니에서 여러개 선택해서
   * 선택한 상품의 갯수와 선택한 상품가격의 합
   */
  @ResponseBody
  @RequestMapping(value="/basket/check.do", 
                            method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String check_book(String checkboxValues) {
    
    String[] basketsplit = null;
    int sum = 0;
    int cnt = 0;
    
    if(checkboxValues.length() != 0){ // 1개이상 체크했을때
        basketsplit= checkboxValues.split("/");
        int[] basketnos = new int[basketsplit.length];
        cnt = basketsplit.length;
        
        for(int i=0;i<basketsplit.length;i++){
          basketnos[i] = Integer.parseInt(basketsplit[i]);
          //System.out.println("basketnos["+i+"]:" + basketnos[i]);
          
          Contents_BasketVO contents_basketVO = basketProc.read(basketnos[i]);
          sum += (contents_basketVO.getPrice() * contents_basketVO.getB_num());
        }
      
    }else{ // 아무것도 체크하지않았을때
        System.out.println("아무것도 체크하지않았을때");
        sum = 0;
        cnt = 0;
    }
    
    JSONObject obj = new JSONObject();
    obj.put("sum", sum);
    obj.put("cnt", cnt);

    return obj.toJSONString();
  }
  
   
}