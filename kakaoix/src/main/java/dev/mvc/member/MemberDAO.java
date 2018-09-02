package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.member.MemberDAO")
public class MemberDAO implements MemberDAOInter {
 
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;
 
  public MemberDAO() {
    System.out.println("--> MemberDAO created.");
  }
  
  @Override
  public int create(MemberVO memberVO) {
    int count = sqlSessionTemplate.insert("member.create", memberVO);
    return count;
  }  
  
  public int checkId(String mid){
    int count = sqlSessionTemplate.selectOne("member.checkId", mid);
    return count;
  }
  
  @Override
  public MemberVO read(int memberno) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.read", memberno);
    return memberVO;
  }
 
  @Override
  public MemberVO readById(String mid) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.readById", mid);
    return memberVO;
  }   
  
  @Override
  public int login(Map<String, Object> map) {
    int count = sqlSessionTemplate.selectOne("member.login", map);
    return count;
  }
 
}
