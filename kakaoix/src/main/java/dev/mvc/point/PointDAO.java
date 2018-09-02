package dev.mvc.point;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("dev.mvc.point.PointDAO")
public class PointDAO implements PointDAOInter{
  
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  
  @Override
  public int create(PointVO pointVO) {
    int count = sqlSessionTemplate.insert("point.create", pointVO);
    return count;
  }
  
  @Override
  public List<PointVO> list(int memberno) {
    List<PointVO> list = sqlSessionTemplate.selectList("point.list", memberno);
    return list;
  }
  
  @Override
  public PointVO read(int orderno) {
    PointVO pointVO = sqlSessionTemplate.selectOne("point.read", orderno);
    return pointVO;
  }

  

}
