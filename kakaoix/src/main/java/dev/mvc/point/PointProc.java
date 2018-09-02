package dev.mvc.point;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("dev.mvc.point.PointProc")
public class PointProc implements PointProcInter {

  @Autowired
  @Qualifier("dev.mvc.point.PointDAO") 
  private PointDAOInter pointDAO = null;
  
  public PointProc(){
    System.out.println("--> PointProc created.");
    
  }

  @Override
  public int create(PointVO pointVO) {
    int count = pointDAO.create(pointVO);
    return count;
  }

  @Override
  public List<PointVO> list(int memberno) {
    List<PointVO> list = pointDAO.list(memberno);
    return list;
  }

  @Override
  public PointVO read(int orderno) {
    PointVO pointVO = pointDAO.read(orderno);
    return pointVO;
  }

  /**
   * 현재 포인트 계산
   * @param list
   * @return
   */
  @Override
  public int calc(List<PointVO> list) {
    int sum = 0;  
    for(int i=0;i<list.size();i++){
      PointVO pointVO = list.get(i);
      int u_point = pointVO.getU_point();
      int s_point = pointVO.getS_point();
      char pstatus = pointVO.getPstatus();
    
      if(pstatus == 'S'){ //적립
        sum += s_point;
        
      }else{              //pstatus가'U'일때, 적립+사용
        sum += s_point;
        sum -=u_point;
      }
    }
    return sum;
  }

  
  
}
