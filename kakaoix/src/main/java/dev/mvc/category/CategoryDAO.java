package dev.mvc.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.category.CategoryDAO")
public class CategoryDAO implements CategoryDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public CategoryDAO() {
    System.out.println("--> CategoryDAO created.");
  }

  @Override
  public int create(CategoryVO categoryVO) {
    int count = sqlSessionTemplate.insert("category.create", categoryVO);
    return count;
  }

  @Override
  public List<CategoryVO> list() {
    List<CategoryVO> list = sqlSessionTemplate.selectList("category.list");
    return list;
  }

  @Override
  public CategoryVO read(int categoryno) {
    return sqlSessionTemplate.selectOne("category.read", categoryno);
  }

  @Override
  public int update(CategoryVO categoryVO ) {
    return sqlSessionTemplate.update("category.update", categoryVO );
  }

  
}





  