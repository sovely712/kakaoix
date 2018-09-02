package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.category.CategoryVO;


@Repository("dev.mvc.contents.ContentsDAO")
public class ContentsDAO implements ContentsDAOInter {

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;
  
  public ContentsDAO(){
    System.out.println("--> ContentsDAO created.");
  }

  @Override
  public int create(ContentsVO contentsVO) {
    int count = sqlSessionTemplate.insert("contents.create", contentsVO);
    return count;
  }

  @Override
  public int search_count(HashMap hashMap) {
    int search_cnt = sqlSessionTemplate.selectOne("contents.search_count", hashMap);
    return search_cnt;
  }
  
  @Override
  public List<ContentsVO> list_search(HashMap hashMap) {
    List<ContentsVO> list = null;
    list = sqlSessionTemplate.selectList("contents.list_search", hashMap);
    return list;
  }
  
  @Override
  public ContentsVO read(int contentsno) {
    return sqlSessionTemplate.selectOne("contents.read", contentsno);
  }

  @Override
  public int increaseCnt(int contentsno) {
    return sqlSessionTemplate.update("contents.increaseCnt", contentsno);
  }
  
  @Override
  public int decreaseNum(int contentsno) {
    return sqlSessionTemplate.update("contents.decreaseNum", contentsno);
  }

 
}
