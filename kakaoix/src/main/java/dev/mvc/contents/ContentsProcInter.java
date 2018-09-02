package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;


public interface ContentsProcInter {

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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno   카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage      현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String word);

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
