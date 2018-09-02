package dev.mvc.order;

import java.util.HashMap;
import java.util.List;


public interface OrderDAOInter {

  /**
   * <Xmp>
   * �ֹ� ��� 
   * <insert id="create" parameterType='OrderVO'>
   * </Xmp>
   * @param orderVO
   * @return ó���� ���ڵ� ����
   */
  public int create(OrderVO orderVO);
  
  /**
   * <Xmp>
   * �ֹ� ��ȣ  
   * <select id="ordernum" resultType="int">
   * </Xmp>
   */
  public int ordernum();
  
  /**
   * <Xmp>
   * �ֹ���ȣ�� �� ��ȸ
   * <select id="read" resultType="OrderVO" parameterType="int">
   * </Xmp>
   */
  public OrderVO read(int orderno);
  
  /**
   * <Xmp>
   * �� ��ȸ ����Ʈ
   * <select id="List_read" resultType="Order_JoinVO" parameterType="int">
   * </Xmp>
   */
  public List<Order_JoinVO> List_read(int ordernum);
  
  
  /**
   * <Xmp>
   * ȸ���� �˻� (��ü)���ڵ� ��
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   */
  public int search_count(HashMap hashMap);
  
  /**
   * <Xmp>�߰�å
   * ȸ��+�ֹ�ó�����º� �˻�+����¡
   * <select id="list_order_search_paging" resultType="Order_JoinVO" parameterType="HashMap">
   * </Xmp>
   */
  public List<Order_JoinVO> list_order_search_paging(HashMap hashMap);
  
  /**
   * <Xmp>
   * �ֹ�ó������ ����
   * <update id="updateStatus" parameterType="OrderVO">
   * </Xmp>
   */
  public int updateStatus(OrderVO orderVO);
  
  /**
   * <Xmp>
   * ��ٱ��� �ֹ��� ���� ���� ���� 
   * <update id="updatePrice" parameterType="OrderVO">
   * </Xmp>
   */
  public int updatePrice(OrderVO orderVO);

}
