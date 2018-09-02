package dev.mvc.point;

import java.util.HashMap;
import java.util.List;


public interface PointDAOInter {
  
  
  /**
   * 등록
   * <xml>
   * <insert id="create" parameterType="PointVO">
   * </xml>
   */
  public int create(PointVO pointVO);
  
  /**
   * 목록
   * <xml>
   * <select id="list" resultType="PointVO" parameterType="int">
   * </xml>
   */
  public List<PointVO> list(int memberno);
  
  /**
   * 조회
   * <xml>
   * <select id="read" resultType="PointVO" parameterType="int">
   * </xml>
   */
  public PointVO read(int orderno);
  

 
}
