package dev.mvc.basket;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.basket.BasketDAO")
public class BasketDAO implements BasketDAOInter {

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public BasketDAO(){
    System.out.println("--> BasketDAO created.");
    
  }
  
  @Override
  public List<Contents_BasketVO> list(int memberno) {
    List<Contents_BasketVO> list = sqlSessionTemplate.selectList("basket.list", memberno);
    return list;
  }

  @Override
  public Contents_BasketVO read(int basketno) {
    Contents_BasketVO contents_basketVO = sqlSessionTemplate.selectOne("basket.read", basketno);
    return contents_basketVO;
  }

  @Override
  public int create(BasketVO basketVO) {
    int count = sqlSessionTemplate.insert("basket.create", basketVO);
    return count;
  }

  @Override
  public int delete(int basketno) {
    int count = sqlSessionTemplate.delete("basket.delete", basketno);
    return count;
  }

  @Override
  public int visible(Contents_BasketVO contents_basketVO) {
    int count = sqlSessionTemplate.update("basket.visible", contents_basketVO);
    return count;
  }

  @Override
  public int count(int memberno) {
    int count = sqlSessionTemplate.selectOne("basket.count", memberno);
    return count;
  }




}
