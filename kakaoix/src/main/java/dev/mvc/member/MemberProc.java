package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.member.Member;
import dev.mvc.member.MemberVO;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
  @Autowired
  @Qualifier("dev.mvc.member.MemberDAO")
  private MemberDAOInter memberDAO;
  
  public MemberProc(){
    System.out.println("--> MemberProc created.");
  }

  @Override
  public int create(MemberVO memberVO) {
    int count = memberDAO.create(memberVO);
    return count;
  }
  
  @Override
  public int checkId(String mid) {
    int count = memberDAO.checkId(mid);
    return count;
  }
  
  @Override
  public MemberVO read(int memberno) {
    MemberVO memberVO = memberDAO.read(memberno);
    
    return memberVO;
  }
 
  @Override
  public MemberVO readById(String mid) {
    MemberVO memberVO = memberDAO.readById(mid);
    
    return memberVO;
  }
  
  @Override
  public int login(String mid, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("mid", mid);
    map.put("passwd", passwd);
    
    int count = memberDAO.login(map);
    
    return count;
  }
  
 
  
}
