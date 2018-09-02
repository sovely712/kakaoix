package dev.mvc.basket;

import java.util.HashMap;
import java.util.List;

public interface BasketProcInter {
  

   /**
   * ���
   * <xml>
   * <select id="list" parameterType="int" resultType="Contents_BasketVO">
   * </xml>
   */
    public List<Contents_BasketVO> list(int memberno);
  
    
    /**
     * ��ȸ
     * <xml>
     * <select id="read" resultType="Contents_BasketVO" parameterType="int">
     * </xml>
    */
    public Contents_BasketVO read(int basketno);
  
    
    /**
     * ���
     * <xml>
     * <insert id="create" parameterType="BasketVO">
     * </xml>
    */
    public int create(BasketVO basketVO);
    
    
    /**
     * ����
     * <xml>
     * <delete id="delete" parameterType="int">
     * </xml>
    */
    public int delete(int basketno);
    
    
    /**
     * ��ǰ ����
     * <xml>
     * <select id="count" resultType="int" parameterType="int">
     * </xml>
    */
    public int count(int memberno);
    

    /**
     * ��ٱ��� visible ���� �ٲٱ�(��ٱ��� ����, �ֹ�������� �ʿ�)
     * <xml>
     * <update id="visible" parameterType="Contents_BasketVO">
     * </xml>
    */
    public int visible(Contents_BasketVO book_basketVO);
    
    
    
    
    

}
