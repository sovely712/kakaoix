package dev.mvc.order;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("dev.mvc.order.OrderDAO")
public class OrderDAO implements OrderDAOInter {

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;
  
  public OrderDAO(){
    System.out.println("-->OrderDAO created.");
  }

  @Override
  public int create(OrderVO orderVO) {
    int count = sqlSessionTemplate.insert("order.create",orderVO);
    return count;
  }
  
  @Override
  public int ordernum() {
    return sqlSessionTemplate.selectOne("order.ordernum");
  }
  
  @Override
  public OrderVO read(int orderno) {
    return sqlSessionTemplate.selectOne("order.read", orderno);
  }
  
  @Override
  public List<Order_JoinVO> List_read(int ordernum) {
   List<Order_JoinVO> list = sqlSessionTemplate.selectList("order.List_read",ordernum);
   return list;
  } 
 
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = sqlSessionTemplate.selectOne("order.search_count", hashMap);
    return cnt;
  }
  
  @Override
  public List<Order_JoinVO> list_order_search_paging(HashMap hashMap) {
    List<Order_JoinVO> list = null;
    list = sqlSessionTemplate.selectList("order.list_order_search_paging", hashMap);
    return list;
  }

  @Override
  public int updateStatus(OrderVO orderVO) {
    int count = sqlSessionTemplate.update("order.updateStatus", orderVO);
    return count;
  }

  @Override
  public int updatePrice(OrderVO orderVO) {
    int count = sqlSessionTemplate.update("order.updatePrice", orderVO);
    return count;
  }


}
