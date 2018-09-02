package dev.mvc.point;

import java.util.HashMap;
import java.util.List;


public interface PointProcInter {
  
  /**
   * ���
   * <xml>
   * <insert id="create" parameterType="PointVO">
   * </xml>
   */
  public int create(PointVO pointVO);
  
  /**
   * ���
   * <xml>
   * <select id="list" resultType="PointVO" parameterType="int">
   * </xml>
   */
  public List<PointVO> list(int memberno);
  
  /**
   * ��ȸ
   * <xml>
   * <select id="read" resultType="PointVO" parameterType="int">
   * </xml>
   */
  public PointVO read(int orderno);
  
  /**
   * ���� ����Ʈ ���
   */
  public int calc(List<PointVO> list);
  
  

}
