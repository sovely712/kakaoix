package dev.mvc.order;

import java.util.HashMap;
import java.util.List;

public interface OrderProcInter {

  /**
   * <Xmp>
   * 주문 등록 
   * <insert id="create" parameterType='OrderVO'>
   * </Xmp>
   */
  public int create(OrderVO orderVO);
  
  /**
   * <Xmp>
   * 주문 번호  
   * <select id="ordernum" resultType="int">
   * </Xmp>
   */
  public int ordernum();
  
  /**
   * <Xmp>
   * 주문번호별 상세 조회
   * <select id="read" resultType="OrderVO" parameterType="int">
   * </Xmp>
   */
  public OrderVO read(int orderno);
  
  /**
   * <Xmp>
   * 상세 조회 리스트
   * <select id="List_read" resultType="Order_JoinVO" parameterType="int">
   * </Xmp>
   */
  public List<Order_JoinVO> List_read(int ordernum);
  
  /**
   * <Xmp>
   * 회원별 검색 (전체)레코드 수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   */
  public int search_count(HashMap hashMap);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param memberno 회원 번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage 현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int memberno, int search_count, int nowPage, String status); 
  
  /**
   * <Xmp>중고책
   * 회원+주문처리상태별 검색+페이징
   * <select id="list_order_search_paging" resultType=""Order_JoinVO"" parameterType="HashMap">
   * </Xmp>
   */
  public List<Order_JoinVO> list_order_search_paging(HashMap hashMap);
  
  /**
   * <Xmp>
   * 주문처리상태 변경
   * <update id="updateStatus" parameterType="OrderVO">
   * </Xmp>
   */
  public int updateStatus(OrderVO orderVO);
  
  /**
   * <Xmp>
   * 장바구니 주문서 가격 관련 변경 
   * <update id="updatePrice" parameterType="OrderVO">
   * </Xmp>
   */
  public int updatePrice(OrderVO orderVO);
 
}
