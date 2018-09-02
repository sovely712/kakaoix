package dev.mvc.member;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.member.MemberVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class MemberCont {

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  public MemberCont() {
    // System.out.println("--> MemberCont created.");
  }

  /**
   * ȸ������ 
   */
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    // System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // /webapp/member/create.jsp

    return mav;
  }
  
  @RequestMapping(value = "/member/create.do", method = RequestMethod.POST)
  public ModelAndView create(RedirectAttributes redirectAttributes,
      HttpServletRequest request, MemberVO memberVO) {
    ModelAndView mav = new ModelAndView();


    int count = 0; // ȸ�� ī��Ʈ

    int countId = memberProc.checkId(memberVO.getMid());

    String sw = "";

    if (countId == 1) { // ID �ߺ��� �޼��� ���
      redirectAttributes.addAttribute("sw", "mid"); // 1 or 0
      redirectAttributes.addAttribute("count", countId); // 1 or 0

    } else {
      count = memberProc.create(memberVO);
      redirectAttributes.addAttribute("sw", "create"); // 1 or 0
      redirectAttributes.addAttribute("count", count); // 1 or 0
    }

    mav.setViewName("redirect:/member/create_message.jsp");
    return mav;
  }

  /**
   * �ߺ� ID �˻� 
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkId.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String checkId(String mid) {

    JSONObject obj = new JSONObject();

    int cnt = memberProc.checkId(mid);
    obj.put("cnt", cnt);

    return obj.toJSONString();
  }

  /**
   * �α��� ��
   */
  @RequestMapping(value = "/member/login.do", method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/login_ck_form"); 

    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_mid = "";         // mid ���� ����
    String ck_mid_save = "";    // mid ���� ���θ� üũ�ϴ� ����
    String ck_passwd = "";      // passwd ���� ����
    String ck_passwd_save = ""; // passwd ���� ���θ� üũ�ϴ� ����

    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i]; // ��Ű ��ü ����

        if (cookie.getName().equals("ck_mid")) {
          ck_mid = cookie.getValue();
        } else if (cookie.getName().equals("ck_mid_save")) {
          ck_mid_save = cookie.getValue(); // Y, N
        } else if (cookie.getName().equals("ck_passwd")) {
          ck_passwd = cookie.getValue(); // 1234
        } else if (cookie.getName().equals("ck_passwd_save")) {
          ck_passwd_save = cookie.getValue(); // Y, N
        }
      }
    }

    mav.addObject("ck_mid", ck_mid);
    mav.addObject("ck_mid_save", ck_mid_save);
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);

    return mav;
  }

  /**
   * �α��� ó��
   */
  @RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
  public ModelAndView login(HttpServletRequest request,
      HttpServletResponse response, HttpSession session, MemberVO memberVO) {
    
    ModelAndView mav = new ModelAndView();

    String mid = memberVO.getMid();
    String passwd = memberVO.getPasswd();

    if (memberProc.login(mid, passwd) != 1) { // �α��� ���н�
      mav.setViewName("redirect:/member/login_message.jsp");

    } else { // �н����� ��ġ�ϴ� ���
      memberVO = memberProc.readById(mid);

      session.setAttribute("memberno", memberVO.getMemberno()); 
      session.setAttribute("mid", mid);
      session.setAttribute("passwd", passwd);
      session.setAttribute("mname", memberVO.getMname());
      
      // -------------------------------------------------------------------
      // mid ���� ��� ����
      // -------------------------------------------------------------------
      String mid_save = Tool.checkNull(memberVO.getMid_save());
      if (mid_save.equals("Y")) { // mid�� ������ ���
        Cookie ck_mid = new Cookie("ck_mid", mid);
        ck_mid.setMaxAge(60 * 60 * 72 * 10); // 30 day, �ʴ���
        response.addCookie(ck_mid);
      } else { // N, mid�� �������� �ʴ� ���
        Cookie ck_mid = new Cookie("ck_mid", "");
        ck_mid.setMaxAge(0);
        response.addCookie(ck_mid);
      }
      // mid�� �������� �����ϴ� CheckBox üũ ����
      Cookie ck_mid_save = new Cookie("ck_mid_save", mid_save);
      ck_mid_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_mid_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password ���� ��� ����
      // -------------------------------------------------------------------
      String passwd_save = Tool.checkNull(memberVO.getPasswd_save());
      if (passwd_save.equals("Y")) { // �н����� ������ ���
        Cookie ck_passwd = new Cookie("ck_passwd", passwd);
        ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, �н����带 �������� ���� ���
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // passwd�� �������� �����ϴ� CheckBox üũ ����
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------

      mav.setViewName("redirect:../index.jsp"); // Ȯ���� ���

    }
    return mav;
  }

  /**
   * �α׾ƿ� ó��
   */
  @RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, HttpSession session) {

    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session ���� ��ü�� ��ϵ� ��� session ���� ����

    mav.setViewName("redirect:/member/logout_message.jsp");

    return mav;
  }

  

  
  


}
