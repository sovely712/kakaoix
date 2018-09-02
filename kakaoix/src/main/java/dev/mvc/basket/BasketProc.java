package dev.mvc.basket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.basket.BasketProc")
public class BasketProc implements BasketProcInter {

  @Autowired
  @Qualifier("dev.mvc.basket.BasketDAO") 
  private BasketDAOInter basketDAO = null;
  
  public BasketProc(){
    System.out.println("--> BasketProc created.");
    
  }

  @Override
  public List<Contents_BasketVO> list(int memberno) {
    List<Contents_BasketVO> list = basketDAO.list(memberno);
    return list;
  }

  @Override
  public Contents_BasketVO read(int basketno) {
    Contents_BasketVO contents_basketVO = basketDAO.read(basketno);
    return contents_basketVO;
  }

  @Override
  public int create(BasketVO basketVO) {
    int count = basketDAO.create(basketVO);
    return count;
  }

  @Override
  public int delete(int basketno) {
    int count = basketDAO.delete(basketno);
    return count;
  }
  
  @Override
  public int count(int memberno) {
    int count = basketDAO.count(memberno);
    return count;
  }

  @Override
  public int visible(Contents_BasketVO contents_basketVO) {
    int count = basketDAO.visible(contents_basketVO);
    return count;
  }



  
  
  
}
