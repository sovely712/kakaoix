package dev.mvc.member;

import java.util.HashMap;
import java.util.List;

public interface MemberProcInter {
  /**
   * 회원 등록
   * <xml>
   * <insert id="create" parameterType="MemberVO">
   * </xml>
   */
  public int create(MemberVO memberVO);   
  
  /**
   * 아이디 중복 확인
   * <xml>
   * <select id="checkId" resultType="int" parameterType="String">
   * </xml>
   */
  public int checkId(String mid);
  
  /**
   * 회원 조회
   * <xml>
   * <select id="read" resultType="MemberVO" parameterType="int">
   * </xml>
   */
  public MemberVO read(int memberno);
  
  /**
   * 아이디 조회
   * <xml>
   * <select id="readById" resultType="MemberVO" parameterType="String">
   * </xml>
   */
  public MemberVO readById(String mid);
  
  /**
   * 로그인 처리
   * <xml>
   * <select id="login"  resultType="int" parameterType="Map">
   * </xml>
   */
  public int login(String mid, String passwd); 
  
}
