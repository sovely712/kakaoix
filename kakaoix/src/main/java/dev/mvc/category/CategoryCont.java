package dev.mvc.category;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CategoryCont {
  
  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc = null;
  
  public CategoryCont() {
  }
  
  /**
   * ���
   */
  @RequestMapping(value="/category/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/category/create"); 
    return mav;
  }

  @RequestMapping(value="/category/create.do", method=RequestMethod.POST)
  public ModelAndView create(CategoryVO categoryVO) {
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    if (categoryProc.create(categoryVO) == 1) {
     
      mav.setViewName("redirect:/category/list.do");
    } else {
      msgs.add("ī�װ� ��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� �� ����: 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back();\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      
      mav.setViewName("/category/message"); 
    }
    return mav;
  }
  
  /**
   * ���
   */
  @RequestMapping(value="/category/list.do", method=RequestMethod.GET)
  public ModelAndView list(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/category/list"); 
    
    List<CategoryVO> list = categoryProc.list();
    mav.addObject("list", list);
    
    return mav;
  }
 
  /**
   * �� ��������
   */
  @ResponseBody
  @RequestMapping(value = "/category/update.do", method = RequestMethod.GET, 
                            produces = "text/plain;charset=UTF-8")
  public String update(int categoryno) {

    CategoryVO categoryVO = categoryProc.read(categoryno);

    JSONObject obj = new JSONObject();
    obj.put("categoryno", categoryno);
    obj.put("title", categoryVO.getTitle());
    obj.put("rdate", categoryVO.getCdate());

    return obj.toJSONString();
  }

  /**
   * ���� ó��
   */
  @RequestMapping(value = "/category/update.do", 
                             method = RequestMethod.POST)
  public ModelAndView update(CategoryVO categoryVO) {
 
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    if (categoryProc.update(categoryVO) == 1) {
     
      mav.setViewName("redirect:/category/list.do");
    } else {
      msgs.add("ī�װ��� ���濡 �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���. �� ���� ���: 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">���</button>");

      mav.addObject("msgs", msgs);
      mav.addObject("links", links);

      mav.setViewName("/category/message"); 
    }

    return mav;
  }
  
  
 
}


