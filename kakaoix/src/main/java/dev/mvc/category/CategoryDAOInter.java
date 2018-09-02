package dev.mvc.category;

import java.util.List;

public interface CategoryDAOInter {
  
  /**
   * 등록
   * <insert id="create" parameterType="CategoryVO">
   * @return
   */
  public int create(CategoryVO categoryVO);
  
  /**
   * 목록
   * <xmp>
   * <select id="list" resultType="CategoryVO">
   * </xmp>
   */
  public List<CategoryVO> list();
  
  /**
   * 한건의 레코드 조회
   * <xmp>
   * <select id="read" resultType="CategoryVO" parameterType="int">
   * </xmp> 
   */
  public CategoryVO read(int categoryno);
  
  /**
   * 레코드를 수정합니다.
   * <xmp>
   * <update id="update" parameterType="CategoryVO">
   * </xmp> 
   */
  public int update(CategoryVO categoryVO ); 
  
  
}






