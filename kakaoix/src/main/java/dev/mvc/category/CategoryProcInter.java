package dev.mvc.category;

import java.util.List;

public interface CategoryProcInter {
  
  /**
   * ���
   * <insert id="create" parameterType="CategoryVO">
   * @return
   */
  public int create(CategoryVO categoryVO);
  
  /**
   * ���
   * <select id="list" resultType="CategoryVO">
   * @return
   */
  public List<CategoryVO> list();
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <xmp>
   * <select id="read" resultType="CategoryVO" parameterType="int">
   * </xmp> 
   */
  public CategoryVO read(int categoryno);
  
  /**
   * ���ڵ带 �����մϴ�.
   * <xmp>
   * <update id="update" parameterType="CategoryVO">
   * </xmp> 
   */
  public int update(CategoryVO categoryVO ); 
  
  
}






