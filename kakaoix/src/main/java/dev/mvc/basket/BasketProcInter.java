package dev.mvc.basket;

import java.util.HashMap;
import java.util.List;

public interface BasketProcInter {
  

   /**
   * 목록
   * <xml>
   * <select id="list" parameterType="int" resultType="Contents_BasketVO">
   * </xml>
   */
    public List<Contents_BasketVO> list(int memberno);
  
    
    /**
     * 조회
     * <xml>
     * <select id="read" resultType="Contents_BasketVO" parameterType="int">
     * </xml>
    */
    public Contents_BasketVO read(int basketno);
  
    
    /**
     * 등록
     * <xml>
     * <insert id="create" parameterType="BasketVO">
     * </xml>
    */
    public int create(BasketVO basketVO);
    
    
    /**
     * 삭제
     * <xml>
     * <delete id="delete" parameterType="int">
     * </xml>
    */
    public int delete(int basketno);
    
    
    /**
     * 상품 갯수
     * <xml>
     * <select id="count" resultType="int" parameterType="int">
     * </xml>
    */
    public int count(int memberno);
    

    /**
     * 장바구니 visible 상태 바꾸기(장바구니 삭제, 주문했을경우 필요)
     * <xml>
     * <update id="visible" parameterType="Contents_BasketVO">
     * </xml>
    */
    public int visible(Contents_BasketVO book_basketVO);
    
    
    
    
    

}
