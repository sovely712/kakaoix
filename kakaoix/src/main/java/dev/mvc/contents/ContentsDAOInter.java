package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;

import dev.mvc.category.CategoryVO;

public interface ContentsDAOInter {

  /**
   * ���
   * <xmp>
   * <insert id="create" parameterType="ContentsVO">
   * </xmp>
   */
  public int create(ContentsVO contentsVO);
  
  /**
   * category�� �˻��� ���ڵ� ����
   * <xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </xmp>
   */
  public int search_count(HashMap hashMap);  
  
  /**
   * �˻�+����¡ ���
   * <xmp> 
   * <select id="list_search" resultType="ContentsVO" parameterType="HashMap">
   * </xmp>
   */
  public List<ContentsVO> list_search(HashMap hashMap);
  
  /**
   * ��ȸ
   * <xmp>
   * <select id="read" resultMap="ContentsVOResultMap" parameterType="int">
   * </xmp>
   */
  public ContentsVO read(int contentsno);
  
  /**
   * ��ȸ�� ����
   * <xmp>
   * <update id="increaseCnt" parameterType="int">
   * </xmp> 
   */
  public int increaseCnt(int contentsno); 
  
  /**
   * ��� ����
   * <xmp>
   * <update id="decreaseNum" parameterType="int">
   * </xmp> 
   */
  public int decreaseNum(int contentsno);  
  

}

