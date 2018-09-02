package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;


public interface ContentsProcInter {

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
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno   ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage      ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word);

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
