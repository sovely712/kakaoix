package dev.mvc.member;

import java.util.HashMap;
import java.util.List;

public interface MemberProcInter {
  /**
   * ȸ�� ���
   * <xml>
   * <insert id="create" parameterType="MemberVO">
   * </xml>
   */
  public int create(MemberVO memberVO);   
  
  /**
   * ���̵� �ߺ� Ȯ��
   * <xml>
   * <select id="checkId" resultType="int" parameterType="String">
   * </xml>
   */
  public int checkId(String mid);
  
  /**
   * ȸ�� ��ȸ
   * <xml>
   * <select id="read" resultType="MemberVO" parameterType="int">
   * </xml>
   */
  public MemberVO read(int memberno);
  
  /**
   * ���̵� ��ȸ
   * <xml>
   * <select id="readById" resultType="MemberVO" parameterType="String">
   * </xml>
   */
  public MemberVO readById(String mid);
  
  /**
   * �α��� ó��
   * <xml>
   * <select id="login"  resultType="int" parameterType="Map">
   * </xml>
   */
  public int login(String mid, String passwd); 
  
}
