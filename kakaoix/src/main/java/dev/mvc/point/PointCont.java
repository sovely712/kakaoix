package dev.mvc.point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


@Controller
public class PointCont {

  @Autowired
  @Qualifier("dev.mvc.point.PointProc")
  private PointProcInter pointProc;
  
  public PointCont(){
    System.out.println("--> PointCont created.");
    
  }
  
  /**
   * 목록
   */
  @RequestMapping(value="/point/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    
    List<PointVO> list = pointProc.list(memberno);
    
    //현재 포인트 계산
    int sum = pointProc.calc(list);
    
    mav.addObject("list", list);
    mav.addObject("sum", sum);
    
    mav.setViewName("/point/list"); 
    
    return mav;
  }
  
  
  

 
   
}
