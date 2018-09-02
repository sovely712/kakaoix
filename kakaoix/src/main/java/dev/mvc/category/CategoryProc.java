package dev.mvc.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.category.CategoryProc")
public class CategoryProc implements CategoryProcInter {
  @Autowired
  @Qualifier("dev.mvc.category.CategoryDAO")
  private CategoryDAOInter categoryDAO = null;
  
  public CategoryProc() {
    System.out.println("--> CategoryProc created.");
  }
  
  @Override
  public int create(CategoryVO categoryVO) {
    int count = categoryDAO.create(categoryVO);
    return count;
  }

  @Override
  public List<CategoryVO> list() {
    List<CategoryVO> list = categoryDAO.list();
    return list;
  }

  @Override
  public CategoryVO read(int categoryno) {
    return categoryDAO.read(categoryno);
  }

  @Override
  public int update(CategoryVO categoryVO) {
    return categoryDAO.update(categoryVO);
  }
  
}



 