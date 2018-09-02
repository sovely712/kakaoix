package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;

import dev.mvc.category.CategoryVO;

public interface ContentsDAOInter {

  /**
   * 등록
   * <xmp>
   * <insert id="create" parameterType="ContentsVO">
   * </xmp>
   */
  public int create(ContentsVO contentsVO);
  
  /**
   * category별 검색된 레코드 갯수
   * <xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </xmp>
   */
  public int search_count(HashMap hashMap);  
  
  /**
   * 검색+페이징 목록
   * <xmp> 
   * <select id="list_search" resultType="ContentsVO" parameterType="HashMap">
   * </xmp>
   */
  public List<ContentsVO> list_search(HashMap hashMap);
  
  /**
   * 조회
   * <xmp>
   * <select id="read" resultMap="ContentsVOResultMap" parameterType="int">
   * </xmp>
   */
  public ContentsVO read(int contentsno);
  
  /**
   * 조회수 증가
   * <xmp>
   * <update id="increaseCnt" parameterType="int">
   * </xmp> 
   */
  public int increaseCnt(int contentsno); 
  
  /**
   * 재고량 감소
   * <xmp>
   * <update id="decreaseNum" parameterType="int">
   * </xmp> 
   */
  public int decreaseNum(int contentsno);  
  

}

